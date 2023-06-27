package com.weibi.wallet.rest.sdk.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class CommonResponse<T> {
    private final static Integer RC_SUCCESS = 0;
    private final static Integer RC_FAILURE = 1;

    private final static String MC_SUCCESS = "SUCCESS";
    private final static String MC_FAILURE = "FAILURE";


    private static CommonResponse<Object> SUCCESS = new CommonResponse<>(RC_SUCCESS, MC_SUCCESS, Collections.emptyList(), null);
    private static CommonResponse<Object> FAILURE = new CommonResponse<>(RC_FAILURE, MC_FAILURE, Collections.emptyList(), null);

//    @Schema(description = "return code, 0=成功;1=失败")
    private Integer rc;

//    @Schema(description = "message code, 国际化编码，客户端国际化使用")
    private String mc;

//    @Schema(description = "message args, 国际化参数，客户端国际化使用")
    private List<Object> ma;

//    @Schema(description = "返回数据")
    private T result;


    private CommonResponse(Integer rc, String mc, List<Object> ma, T result) {
        this.rc = rc;
        this.mc = mc;
        this.ma = ma;
        this.result = result;
    }

    /* SUCCESS ----------------------------------------------- */

    public static CommonResponse<Object> success() {
        return SUCCESS;
    }

    public static <T> CommonResponse<T> success(String mc) {
        return new CommonResponse<>(RC_SUCCESS, mc, Collections.emptyList(), null);
    }

    public static <T> CommonResponse<T> success(String mc, List<Object> ma) {
        return new CommonResponse<>(RC_SUCCESS, mc, ma, null);
    }

    public static <T> CommonResponse<T> success(String mc, List<Object> ma, T result) {
        return new CommonResponse<>(RC_SUCCESS, mc, ma, result);
    }

    public static <T> CommonResponse<T> successOf(T result) {
        return new CommonResponse<>(RC_SUCCESS, MC_SUCCESS, Collections.emptyList(), result);
    }


    /* FAILURE ----------------------------------------------- */

    public static CommonResponse<Object> failure() {
        return FAILURE;
    }

    public static <T> CommonResponse<T> failure(String mc) {
        return new CommonResponse<>(RC_FAILURE, mc, Collections.emptyList(), null);
    }

    public static <T> CommonResponse<T> failure(String mc, List<Object> ma) {
        return new CommonResponse<>(RC_FAILURE, mc, ma, null);
    }


    /* Custom ----------------------------------------------- */

    public static <T> CommonResponse<T> custom(Integer rc, String mc, List<Object> ma, T result) {
        return new CommonResponse<>(rc, mc, ma, result);
    }


    /* Deprecated ----------------------------------------------- */

    /**
     * 推荐使用successOf(T result)
     *
     * @param result
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T> CommonResponse<T> of(T result) {
        return successOf(result);
    }

    /**
     * 推荐使用 failure(mc)
     *
     * @param msg
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T> CommonResponse<T> error(String msg) {
        return failure(msg);
    }

}
