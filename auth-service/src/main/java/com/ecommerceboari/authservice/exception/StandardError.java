package com.ecommerceboari.authservice.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StandardError {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
