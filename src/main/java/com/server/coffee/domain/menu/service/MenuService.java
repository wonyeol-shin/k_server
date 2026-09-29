package com.server.coffee.domain.menu.service;

import com.server.coffee.common.api.PageResponse;
import com.server.coffee.domain.menu.dto.response.MenuListResponse;
import com.server.coffee.domain.menu.entity.Menu;
import com.server.coffee.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    @Cacheable(value = "menu_list", key = "#pageable.pageSize + ':' + #pageable.pageNumber")
    public PageResponse<MenuListResponse> getAll(Pageable pageable) {
        Page<Menu> page = menuRepository.findAll(pageable);
        return PageResponse.of(page, MenuListResponse::from);
    }
}
