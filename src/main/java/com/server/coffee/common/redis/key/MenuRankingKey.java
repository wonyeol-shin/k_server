package com.server.coffee.common.redis.key;

import java.time.LocalDate;

public final class MenuRankingKey {

    private MenuRankingKey() {}

    public static String dailyTopMenuRanking(LocalDate date){return "ORDERED_MENU:" + date + ":TOP_THREE_MENU";}
}
