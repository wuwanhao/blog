package com.wwh.utils;

import lombok.Data;

@Data
public class Result<T> {
    private boolean success = true;
    private int errcode = 200;
    private String message = "";
    private T data;

    public Result(T data) {
        this.message = "success";
        this.data = data;
    }

    public Result(int errcode, String message) {
        this.success = false;
        this.errcode = errcode;
        this.message = message;
        this.data = null;
    }

    public Result(boolean success, String message) {
        this.success = true;
        this.errcode = 200;
        this.message = message;
        this.data = null;
    }

}
