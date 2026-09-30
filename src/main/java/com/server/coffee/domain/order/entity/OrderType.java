package com.server.coffee.domain.order.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderType {
    COMPLETE("COMPLETE")
    ;

    private final String type;
}
