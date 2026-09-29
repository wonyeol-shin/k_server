package com.server.coffee.domain.point.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record ChargeRequest(
        @NotEmpty
        String nickname,
        @Min(1_000) @Max(1_000_000)
        int point
) {
}
