package com.server.coffee.domain.menu.dto.response;

import com.server.coffee.domain.menu.entity.Menu;

public record MenuListResponse(
        Long id,
        String name,
        int price
) {
    public static MenuListResponse from(Menu menu) {
        return new MenuListResponse(menu.getId(), menu.getName(), menu.getPrice());
    }

}
