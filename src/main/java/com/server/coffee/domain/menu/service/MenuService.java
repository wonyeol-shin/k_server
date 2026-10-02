package com.server.coffee.domain.menu.service;

import com.server.coffee.common.api.PageResponse;
import com.server.coffee.common.exception.BusinessException;
import com.server.coffee.common.exception.ErrorCode;
import com.server.coffee.common.redis.key.MenuRankingKey;
import com.server.coffee.common.redis.key.OrderKey;
import com.server.coffee.domain.menu.dto.response.MenuListResponse;
import com.server.coffee.domain.menu.entity.Menu;
import com.server.coffee.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final StringRedisTemplate stringRedisTemplate;

    @Cacheable(value = "menu_list", key = "#pageable.pageSize + ':' + #pageable.pageNumber")
    public PageResponse<MenuListResponse> getAll(Pageable pageable) {
        Page<Menu> page = menuRepository.findAll(pageable);
        return PageResponse.of(page, MenuListResponse::from);
    }

    public Menu findMenu(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(
                () -> new BusinessException(ErrorCode.NOT_FOUND_MENU)
        );
    }

    public List<MenuListResponse> getTopTreeMenuInSevenDay() {
        // 오늘날짜부터 7일전까지 키를 가져옴
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        List<String> keys = new ArrayList<>();
        for (int i=0; i<7; i++){
            LocalDate targetDate = today.minusDays(i);
            keys.add(OrderKey.dailyOrder(targetDate));
        }

        // 7일간 주문 목록을 합침, 단, 5분동안 캐싱하여 조회시마다 생성하지 않게 방지
        String key = MenuRankingKey.dailyTopMenuRanking(today);
        if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(key))) {
            stringRedisTemplate.opsForZSet()
                    .unionAndStore(keys.get(0),keys.subList(1, keys.size()),key);
            stringRedisTemplate.expire(key, Duration.ofMinutes(5));
        }

        // 합친 목록에서 top3항목 가져옴
        Set<TypedTuple<String>> set = stringRedisTemplate.opsForZSet()
                .reverseRangeWithScores(key, 0, 2);
        if (set == null || set.isEmpty()){
            return List.of();
        }

        // 인기순서로 응답을 위해 list로 변환
        List<Long> popularIds = new ArrayList<>();
        for (TypedTuple<String> tuple : set) {
            if (tuple.getValue() == null) {
                continue;
            }
            popularIds.add(Long.valueOf(tuple.getValue()));
        }

        // DB조회 후 인기 순서대로 값을 뽑아서 전달
        Map<Long,Menu> menuMap = menuRepository.findByIds(popularIds).stream()
                .collect(Collectors.toMap(Menu::getId, menu -> menu));
        List<MenuListResponse> menuListResponses = new ArrayList<>();
        for (Long menuId : popularIds) {
            Menu menu = menuMap.get(menuId);
            if (menu == null){
                continue;
            }
            menuListResponses.add(new MenuListResponse
                    (menu.getId(), menu.getName(), menu.getPrice()));
        }
       return menuListResponses;
    }
}
