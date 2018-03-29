package com.base.entity.response;

public class SuccessResponse extends BaseResponse {
    public SuccessResponse(Object data) {
        super(0, "Executed Successfully", data);
    }

    public SuccessResponse() {
        super(0, "done successfully", null);
    }
}
