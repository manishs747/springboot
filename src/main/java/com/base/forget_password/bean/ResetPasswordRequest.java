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
public class ResetPasswordRequest {

    @JsonProperty("old_password")
    private String oldPassWord;

    @JsonProperty("new_password")
    private String newPassword;

    @JsonProperty("token")
    String token;
}
