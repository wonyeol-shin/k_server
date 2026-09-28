package com.server.coffee.domain.menu.controller;

import com.server.coffee.common.api.ApiResponse;
import com.server.coffee.domain.menu.service.MenuBulkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bulk")
public class MenuController {

    private final MenuBulkService menuBulkService;

    @PostMapping("/coffees")
    public ResponseEntity<ApiResponse<Void>> bulkCoffee(){
        menuBulkService.createBulkMenu();
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
