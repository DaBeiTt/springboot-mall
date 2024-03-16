package org.hsiaomartin.springbootmall.rowmapper;

import org.hsiaomartin.springbootmall.constant.InformationType;
import org.hsiaomartin.springbootmall.model.Information;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InformationRowMapper implements RowMapper<Information> {

    @Override
    public Information mapRow(ResultSet rs, int rowNum) throws SQLException {

        Information info = new Information();
        info.setInfoId(rs.getInt("info_id"));
        info.setInfoType(InformationType.valueOf(rs.getString("type")));
        info.setTitle(rs.getString("title"));
        info.setDescription(rs.getString("description"));
        info.setContent(rs.getString("content"));
        info.setImageUrl(rs.getString("image_url"));
        info.setCreatedDate(rs.getTimestamp("created_date"));
        info.setLastModefiedDate(rs.getTimestamp("last_modified_date"));

        return info;
    }
}
