package com.kylin.biz.sdk.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResponse<T> {
    private static CommonResponse<Object> SUCCESS = new CommonResponse(0, "success");
    private static CommonResponse<Object> FAILURE = new CommonResponse(1, "failure");
    private Integer code;
    private String msg;
    private T data;

    private CommonResponse(Integer returnCode, String msgInfo) {
        this.code = returnCode;
        this.msg = msgInfo;
    }

    private CommonResponse(T result) {
        this.code = 0;
        this.msg = "success";
        this.data = result;
    }

    public static CommonResponse<Object> success() {
        return SUCCESS;
    }

    public static <T> CommonResponse<T> error(String msg) {
        CommonResponse<T> commonResponse = new CommonResponse(-1, "failure");
        commonResponse.setMsg(msg);
        return commonResponse;
    }

}
