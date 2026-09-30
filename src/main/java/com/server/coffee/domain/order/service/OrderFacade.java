package com.server.coffee.domain.order.service;

import com.server.coffee.common.lock.LockDistributeService;
import com.server.coffee.common.lock.LockKey;
import com.server.coffee.domain.order.dto.response.CreateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderCommandService orderCommandService;
    private final LockDistributeService lockDistributeService;

    public CreateOrderResponse order(Long menuId, String nickname) {
        return lockDistributeService.execute(
                LockKey.point(nickname),
                () -> orderCommandService.order(menuId,nickname)
        );
    }
}
