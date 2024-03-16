package org.hsiaomartin.springbootmall.dao.impl;

import org.hsiaomartin.springbootmall.constant.InformationType;
import org.hsiaomartin.springbootmall.dao.InformationDao;
import org.hsiaomartin.springbootmall.model.Information;
import org.hsiaomartin.springbootmall.rowmapper.InformationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InformationDaoImpl implements InformationDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Information> getInfosByType(InformationType type) {

        String typeString = type.name();

        String sql = "SELECT info_id, type, title, description, content, image_url, created_date, last_modified_date" +
                " FROM information WHERE type = :type";

        Map<String, Object> map = new HashMap<>();
        map.put("type", typeString);

        List<Information> infoList = namedParameterJdbcTemplate.query(sql, map, new InformationRowMapper());

        return infoList;
    }

    @Override
    public Information getInfoById(Integer infoId) {

        String sql = "SELECT info_id, type, title, description, content, image_url, created_date, last_modified_date" +
                " FROM information WHERE info_id = :id";

        Map<String, Object> map = new HashMap<>();
        map.put("id", infoId);

        List<Information> infoList = namedParameterJdbcTemplate.query(sql, map, new InformationRowMapper());

        if(infoList.size() > 0) {
            return infoList.get(0);
        }
        else {
            return null;
        }
    }
}
