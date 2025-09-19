package org.hsiaomartin.springbootmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.hsiaomartin.springbootmall.model.Information;

@Mapper
public interface InformationMapper extends BaseMapper<Information> {
}
