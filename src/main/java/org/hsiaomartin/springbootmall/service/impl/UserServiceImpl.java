package org.hsiaomartin.springbootmall.service.impl;

import org.hsiaomartin.springbootmall.dao.UserDao;
import org.hsiaomartin.springbootmall.dto.UserRegisterRequest;
import org.hsiaomartin.springbootmall.model.User;
import org.hsiaomartin.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);
    }
}
