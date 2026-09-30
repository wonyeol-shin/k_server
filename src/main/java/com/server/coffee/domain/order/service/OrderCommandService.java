package com.server.coffee.domain.order.service;

import com.server.coffee.domain.menu.entity.Menu;
import com.server.coffee.domain.menu.service.MenuService;
import com.server.coffee.domain.order.dto.response.CreateOrderResponse;
import com.server.coffee.domain.order.entity.Order;
import com.server.coffee.domain.point.service.PointHistoryService;
import com.server.coffee.domain.user.entity.User;
import com.server.coffee.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderCommandService {

    private final UserService userService;
    private final MenuService menuService;
    private final OrderService orderService;
    private final PointHistoryService pointHistoryService;

    public CreateOrderResponse order(Long menuId, String nickname) {
        User user = userService.findUser(nickname);
        Menu menu = menuService.findMenu(menuId);
        Order order = orderService.createOrder(user, menu);
        pointHistoryService.use(user, menu.getPrice(), order);
        return new CreateOrderResponse(
                order.getId(),
                nickname,
                order.getPrice(),
                order.getType().toString()
        );
    }
}
