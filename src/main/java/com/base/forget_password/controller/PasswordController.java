package com.base.forget_password.controller;

import com.base.entity.response.BaseResponse;
import com.base.entity.response.ErrorResponse;
import com.base.entity.response.SuccessResponse;
import com.base.forget_password.bean.ForgetUrlResponse;
import com.base.forget_password.bean.ResetPasswordRequest;
import com.base.forget_password.bean.UserEntity;
import com.base.forget_password.service.ForgetPasswordService;
import com.base.forget_password.service.UserService;
import com.base.utils.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/v1/user")
public class PasswordController {


    private static final int ERROR_CODE = 100;
    private static final String INVALID_TOKEN = "Invalid Token";
    private static final String ERROR_MSG = "Some error on server  side for user controller";
    private static final String USER_NOT_FOUND_ERROR_MSG = "We didn't find an account for given email.";
    private static final String RESET_PASSWORD_RESPONSE = "You have successfully reset your password.  You may now login.";
    private static final String OLD_PASSWORD_NOT_MATCHING = "Old password is not matching";


    @Autowired
    ForgetPasswordService forgetPasswordService;

    @Autowired
    UserService userService;

    @RequestMapping(
            value = "/forget",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse getPatient(HttpServletRequest request, @RequestParam("user_email") String userEmail) {
        try {
            log.info("User id {}", userEmail);
            String token = forgetPasswordService.getTokenForUser(userEmail);
            if (token == null) {
                return new ErrorResponse(ERROR_CODE, USER_NOT_FOUND_ERROR_MSG);
            }
            String appUrl = request.getScheme() + "://" + request.getServerName() + ":8080/api/v1/user/reset";
            ForgetUrlResponse forgetUrlResponse = ForgetUrlResponse.builder().token(token).url(appUrl).build();
            return new SuccessResponse(forgetUrlResponse);
        } catch (Exception e) {
            return new ErrorResponse(ERROR_CODE, ERROR_MSG);
        }
    }


    @RequestMapping(
            value = {"/reset"},
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse updatePassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            String token = resetPasswordRequest.getToken();
            if (token == null || token.isEmpty()) {
                return new ErrorResponse(ERROR_CODE, INVALID_TOKEN);
            }
            UserEntity user = userService.findUserByResetToken(token);
            if (user == null) {
                return new ErrorResponse(ERROR_CODE, "No User Present with token:" + token);
            }
            String oldPassword = resetPasswordRequest.getOldPassWord();
            String oldPasswordExisting = Utility.decodeBase64(user.getPassword());
            if (!oldPasswordExisting.equals(oldPassword)) {
                return new ErrorResponse(ERROR_CODE, OLD_PASSWORD_NOT_MATCHING);
            }
            String newPassWord = Utility.encodeBase64(resetPasswordRequest.getNewPassword());
            user.setPassword(newPassWord);
            user.setResetToken(null);
            userService.save(user);
            return new SuccessResponse(RESET_PASSWORD_RESPONSE);
        } catch (Exception e) {
            return new ErrorResponse(ERROR_CODE, ERROR_MSG);
        }
    }


}
