package com.server.coffee.domain.order.dto.response;

public record CreateOrderResponse(
        Long id,
        String nickname,
        int price,
        String type
) {
}
