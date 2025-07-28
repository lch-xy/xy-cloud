package org.xy.cloud.framework.common.exception.enums;

import org.xy.cloud.framework.common.exception.ErrorCode;

public interface ErrorCodeContants {

    ErrorCode SUCCESS = new ErrorCode(0,"成功");


    ErrorCode BAD_REQUSET = new ErrorCode(400,"请求参数不正确");

    ErrorCode UNAUTHORIZATION = new ErrorCode(401,"账号未登录");

    ErrorCode FORBIDDENN = new ErrorCode(403,"没有该操作权限");

    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500,"系统异常");


}
