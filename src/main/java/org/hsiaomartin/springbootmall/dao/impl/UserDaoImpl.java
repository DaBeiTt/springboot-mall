package org.hsiaomartin.springbootmall.dao.impl;

import org.hsiaomartin.springbootmall.dao.UserDao;
import org.hsiaomartin.springbootmall.dto.UserRegisterRequest;
import org.hsiaomartin.springbootmall.model.User;
import org.hsiaomartin.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql = "INSERT INTO `user`(email, password, created_date, last_modified_date) " +
                "VALUES(:email, :password, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        map.put("createdDate", new Date());
        map.put("lastModifiedDate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public User getUserById(Integer userId) {

        String sql = "SELECT user_id, email, password, created_date, last_modified_date" +
                " FROM `user` WHERE user_id = :userId";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> user = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(user.size() > 0) {
            return user.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {

        String sql = "SELECT user_id, email, password, created_date, last_modified_date" +
                " FROM `user` WHERE email = :userEmail";

        Map<String, Object> map = new HashMap<>();
        map.put("userEmail", email);

        List<User> user = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if(user.size() > 0) {
            return user.get(0);
        }
        else {
            return null;
        }

    }
}
