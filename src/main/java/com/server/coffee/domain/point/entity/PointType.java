package com.server.coffee.domain.point.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PointType {
    CHARGE("CHARGE"),
    USED("USED"),
    ;

    private final String type;

}
