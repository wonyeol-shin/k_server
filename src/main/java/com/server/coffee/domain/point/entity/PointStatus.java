package com.server.coffee.domain.point.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PointStatus {
    CHARGE("CHARGE"),
    USED("USED"),
    ;

    private final String status;

}
