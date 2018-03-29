package com.base.entity.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaseResponse {
    private int statusCode;
    private String statusMessage;
    private Object data;

    private BaseResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public BaseResponse(int statusCode, String statusMessage, Object data) {
        this(statusCode, statusMessage);
        this.data = data;
    }
}
