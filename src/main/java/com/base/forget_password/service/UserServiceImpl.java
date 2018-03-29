package com.base.forget_password.service;

import com.base.forget_password.bean.UserEntity;
import com.base.forget_password.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity findUserByEmail(String email) {
        return userDao.findOneByEmail(email);
    }

    @Override
    public UserEntity findUserByResetToken(String resetToken) {
        return userDao.findOneByResetToken(resetToken);
    }

    @Override
    public void save(UserEntity user) {
        userDao.save(user);
    }
}
