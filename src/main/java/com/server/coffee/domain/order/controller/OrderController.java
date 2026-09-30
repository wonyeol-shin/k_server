package com.server.coffee.domain.order.controller;

import com.server.coffee.common.api.ApiResponse;
import com.server.coffee.domain.order.dto.request.CreateOrderRequest;
import com.server.coffee.domain.order.dto.response.CreateOrderResponse;
import com.server.coffee.domain.order.service.OrderFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateOrderResponse>> createOrder(
            @RequestBody @Valid CreateOrderRequest request
    ){
        return ResponseEntity.ok(ApiResponse.ok(
                orderFacade.order(request.id(),request.nickname())));
    }
}
