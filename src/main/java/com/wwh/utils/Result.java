package com.wwh.utils;

import lombok.Data;

@Data
public class Result<T> {
    // 状态码
    private int code = 200;

    // 请求状态信息
    private String msg = "";

    // 实际的数据
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }



    // 请求成功，并返回数据
    public static Result success(Object data) {
        return new Result<Object>(0, "success", data);
    }

    // 请求发生错误：返回错误信息
    public static Result error(int code, String msg) {
        return new Result<Object>(code, msg, null);
    }

}
