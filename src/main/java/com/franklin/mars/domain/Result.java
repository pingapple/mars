package com.franklin.mars.domain;

/**
 * 结果模板
 *
 * @param <T>
 */

public final class Result<T> {


    private static final String ERROR_CDOE = "409";
    private static final String SUCCESS_MSG = "successful";
    private String code;
    private String msg;
    private T data;
    private static final String SUCCESS_CODE = "200";

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> setOk(String msg, T data) {
        return new Result<>(SUCCESS_CODE, msg, data);
    }

    public static <T> Result<T> setOk(T data) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> Result<T> setError(String msg, T data) {
        return new Result<>(ERROR_CDOE, msg, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
