package com.base.forget_password.service;


import com.base.forget_password.bean.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ForgetPasswordServiceImp implements ForgetPasswordService {

    @Autowired
    UserService userService;

    public String getTokenForUser(String email) {

        UserEntity user = userService.findUserByEmail(email);
        log.info("User for email {} is {}", email, user);
        if (user == null) {
            return null;
        }
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userService.save(user);
        return token;
    }
}
