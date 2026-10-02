package com.server.coffee.domain.order.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateOrderRequest(
        @NotNull @Min(1)
        Long id,
        @NotEmpty
        String nickname,
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate orderDate
) {
}
