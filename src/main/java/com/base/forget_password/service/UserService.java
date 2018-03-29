package com.base.forget_password.service;

import com.base.forget_password.bean.UserEntity;

public interface UserService {
     UserEntity findUserByEmail(String email);

     UserEntity findUserByResetToken(String resetToken);

     void save(UserEntity user);
}
