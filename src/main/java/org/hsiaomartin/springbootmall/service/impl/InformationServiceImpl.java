package org.hsiaomartin.springbootmall.service.impl;

import org.hsiaomartin.springbootmall.constant.InformationType;
import org.hsiaomartin.springbootmall.dao.InformationDao;
import org.hsiaomartin.springbootmall.model.Information;
import org.hsiaomartin.springbootmall.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationDao infoDao;

    @Override
    public List<Information> getInfosByType(InformationType type) {

        return infoDao.getInfosByType(type);
    }

    @Override
    public Information getInfoById(Integer infoId) {

        Information info = infoDao.getInfoById(infoId);

        return info;
    }
}
