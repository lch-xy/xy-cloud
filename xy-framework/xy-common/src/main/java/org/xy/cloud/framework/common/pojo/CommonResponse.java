package org.xy.cloud.framework.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> {

    private int code;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(200, "OK", data);
    }

    public static CommonResponse<Void> error(int code, String message) {
        return new CommonResponse<>(code, message, null);
    }
}
