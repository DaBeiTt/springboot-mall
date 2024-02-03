package org.hsiaomartin.springbootmall.dao;

import org.hsiaomartin.springbootmall.dto.UserRegisterRequest;
import org.hsiaomartin.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
