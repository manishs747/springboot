package com.base.forget_password.dao;


import com.base.forget_password.bean.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long> {

    UserEntity findOneByEmail(String email);

    UserEntity findOneByResetToken(String resetToken);
}
