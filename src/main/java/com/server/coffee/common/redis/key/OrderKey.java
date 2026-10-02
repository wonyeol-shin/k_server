package com.server.coffee.common.redis.key;

import java.time.LocalDate;

public final class OrderKey {

    private OrderKey(){}

    public static String dailyOrder(LocalDate date){
        return "ORDERED_MENU:" + date;
    }
}
