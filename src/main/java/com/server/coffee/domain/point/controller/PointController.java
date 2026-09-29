package com.server.coffee.domain.point.controller;

import com.server.coffee.common.api.ApiResponse;
import com.server.coffee.domain.point.dto.request.ChargeRequest;
import com.server.coffee.domain.point.service.PointHistoryFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointController {

    private final PointHistoryFacade pointHistoryFacade;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> charge(
            @RequestBody @Valid ChargeRequest request)
    {
        pointHistoryFacade.charge(request.nickname(), request.point());
       return ResponseEntity.ok(ApiResponse.ok());
    }
}
