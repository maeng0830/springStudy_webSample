package com.maeng0830.websample.dto;

import com.maeng0830.websample.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;
}
