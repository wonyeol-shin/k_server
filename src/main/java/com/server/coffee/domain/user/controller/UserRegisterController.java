package com.server.coffee.domain.user.controller;

import com.server.coffee.common.api.ApiResponse;
import com.server.coffee.domain.user.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> registerUser() {
        userRegisterService.registerTmpUser();
        return ResponseEntity.ok(ApiResponse.ok());
    }
}
