package org.hsiaomartin.springbootmall.service.impl;

import lombok.RequiredArgsConstructor;
import org.hsiaomartin.springbootmall.constant.InformationType;
import org.hsiaomartin.springbootmall.dao.InformationRepository;
import org.hsiaomartin.springbootmall.model.Information;
import org.hsiaomartin.springbootmall.service.InformationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {

    private final InformationRepository informationRepository;

    @Override
    public List<Information> getInfosByType(InformationType type) {
        return informationRepository.findByType(type);
    }

    @Override
    public Information getInfoById(Integer infoId) {
        return informationRepository.findById(infoId).orElse(null);
    }
}
