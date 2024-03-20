package org.hsiaomartin.springbootmall.service;

import org.hsiaomartin.springbootmall.dto.UserLoginRequest;
import org.hsiaomartin.springbootmall.dto.UserRegisterRequest;
import org.hsiaomartin.springbootmall.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
