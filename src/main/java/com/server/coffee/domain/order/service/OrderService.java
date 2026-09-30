package com.server.coffee.domain.order.service;

import com.server.coffee.domain.menu.entity.Menu;
import com.server.coffee.domain.order.entity.Order;
import com.server.coffee.domain.order.repository.OrderRepository;
import com.server.coffee.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order createOrder(User user, Menu menu) {
        return orderRepository.save(
                Order.complete(menu.getPrice(), user, menu)
        );
    }
}
