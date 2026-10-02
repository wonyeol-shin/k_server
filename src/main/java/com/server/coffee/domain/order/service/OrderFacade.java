package com.server.coffee.domain.order.service;

import com.server.coffee.common.lock.LockDistributeService;
import com.server.coffee.common.lock.LockKey;
import com.server.coffee.common.redis.key.OrderKey;
import com.server.coffee.domain.order.dto.response.CreateOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final StringRedisTemplate stringRedisTemplate;
    private final OrderCommandService orderCommandService;
    private final LockDistributeService lockDistributeService;

    public CreateOrderResponse order(Long menuId, String nickname, LocalDate date) {
        CreateOrderResponse response = lockDistributeService.execute(
                LockKey.pointLock(nickname),
                () -> orderCommandService.order(menuId,nickname)
        );

        String key = OrderKey.dailyOrder(date);
        try{
            stringRedisTemplate.opsForZSet().incrementScore(key, String.valueOf(menuId), 1);
            stringRedisTemplate.expire(key, Duration.ofDays(8));
        }catch (RedisConnectionFailureException e){
            log.error("redis 처리 실패!! OrderId: {} Date: {}", response.id(), date);
        }

        return response;
    }
}
