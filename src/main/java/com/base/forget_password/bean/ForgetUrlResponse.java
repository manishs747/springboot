package com.base.forget_password.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForgetUrlResponse {
    @JsonProperty("url")
    private String url;

    @JsonProperty("token")
    String token;
}
