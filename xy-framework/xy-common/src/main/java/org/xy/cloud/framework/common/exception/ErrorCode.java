package org.xy.cloud.framework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorCode {

    private final int code;
    private final String message;

}
