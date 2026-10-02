package com.server.coffee.domain.menu.controller;

import com.server.coffee.common.api.ApiResponse;
import com.server.coffee.common.api.PageResponse;
import com.server.coffee.domain.menu.dto.response.MenuListResponse;
import com.server.coffee.domain.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coffees")
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<MenuListResponse>>> getAllCoffee(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(ApiResponse.ok(menuService.getAll(pageable)));
    }

    @GetMapping("/top-ordered")
    public ResponseEntity<ApiResponse<List<MenuListResponse>>> getTopOrdered(){
        return ResponseEntity.ok(ApiResponse.ok(menuService.getTopTreeMenuInSevenDay()));
    }
}
