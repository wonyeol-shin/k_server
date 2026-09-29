package com.server.coffee.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "MEMBER_001", "없는 사용자 입니다."),

    INVALID_POINT_AMOUNT(HttpStatus.BAD_REQUEST, "POINT_001", "포인트는 최소 1000원부터 최대 100만원까지 충전 가능합니다."),

    NOT_SUFFICIENT(HttpStatus.BAD_REQUEST,"ORDER_001", "포인트가 부족합니다."),

    INVALID_INPUT(HttpStatus.BAD_REQUEST, "COMMON_001", "입력값이 잘못 되었습니다." ),

    TIMEOUT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_001", "잠시 후 다시 시도해 주세요" )
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
