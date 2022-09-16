package com.maeng0830.websample.exception;

import com.maeng0830.websample.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 모든 Controller에 적용되는 사항들
public class GlobalExceptionHandler { // 모든 Controller에 적용되는 예외 처리
    // 예외 처리 1
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorResponse> handleIllegalAccessException(
            IllegalAccessException e) {
        log.error("IllegalAccessException is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ErrorCode.TOO_BIG_ID_ERROR,
                        "IllegalAccessException"));
    }

    // 예외 처리 2
    // orderId = 500 or orderId = 3 -> WebSampleException 발생
    @ExceptionHandler(WebSampleException.class)
    public ResponseEntity<ErrorResponse> handleWebSampleException(
            WebSampleException e) {
        log.error("WebSampleException is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.INSUFFICIENT_STORAGE)
                .body(new ErrorResponse(e.getErrorCode(),
                        "WebSampleException"));
    }

    // 예외 처리 3
    // 그 외 다른 모든 예외들
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleWebSampleException(
            Exception e) {
        log.error("Exception is occurred.", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR,
                        "Exception is occurred."));
    }
}
