package com.server.coffee.domain.order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
        @NotNull @Min(1)
        Long id,
        @NotEmpty
        String nickname
) {
}
