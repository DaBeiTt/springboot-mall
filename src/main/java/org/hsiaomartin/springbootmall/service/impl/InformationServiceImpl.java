package org.hsiaomartin.springbootmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.hsiaomartin.springbootmall.constant.InformationType;
import org.hsiaomartin.springbootmall.dao.InformationMapper;
import org.hsiaomartin.springbootmall.model.Information;
import org.hsiaomartin.springbootmall.service.InformationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {

    private final InformationMapper mapper;

    @Override
    public List<Information> getInfosByType(InformationType type) {
        QueryWrapper<Information> wrapper = new QueryWrapper<>();
        wrapper.eq(Information.Fields.type, type.name());
        return mapper.selectList(wrapper);
    }

    @Override
    public Information getInfoById(Integer infoId) {
        return mapper.selectById(infoId);
    }
}
