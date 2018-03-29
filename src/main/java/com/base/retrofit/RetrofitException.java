package com.base.retrofit;

public class RetrofitException extends Exception {
    private int code;
    private String message;
    private String detailMessage;

    public RetrofitException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
