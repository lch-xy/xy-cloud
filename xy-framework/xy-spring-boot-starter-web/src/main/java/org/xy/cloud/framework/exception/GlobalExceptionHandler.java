package org.xy.cloud.framework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xy.cloud.framework.common.pojo.CommonResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.xy.cloud.framework.common.exception.enums.ErrorCodeContants.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ArithmeticException.class)
    public CommonResponse<?> handleArithmeticException(Exception e) {
        log.error("handleArithmeticException:", e);
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return CommonResponse.error(INTERNAL_SERVER_ERROR.getCode(),sw.toString());
    }


}
