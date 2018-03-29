package com.base.entity.response;

import lombok.Getter;
import lombok.Setter;

public class BaseError  extends Throwable {
    @Getter
    @Setter
    private int statusCode;
    @Getter @Setter private String statusMessage;

    public BaseError(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
