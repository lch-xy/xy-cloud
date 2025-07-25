package org.xy.cloud.framework.common.exception;

import lombok.Data;

@Data
public class ErrorCode {

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
