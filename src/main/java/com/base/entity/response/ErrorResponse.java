package com.base.entity.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ErrorResponse extends BaseResponse{
    public ErrorResponse(Object data){
        super(1,"error occured",data);
    }

    public ErrorResponse(String message) {
        super(1, message, null);
    }

    public ErrorResponse(String status,Object data){
        super(1,status,data);
    }

    public ErrorResponse(BaseError e,Object data){
        super(e.getStatusCode(),e.getStatusMessage(),data);
    }

    public ErrorResponse(BaseError e){
        this(e,null);
    }

    public ErrorResponse(int statusCode, String message) {
        super(statusCode, message, null);
    }

    public ErrorResponse(int statusCode, String message, Object data) {
        super(statusCode, message, data);
    }
}

