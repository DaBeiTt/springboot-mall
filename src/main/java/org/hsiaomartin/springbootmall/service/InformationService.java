package org.hsiaomartin.springbootmall.service;

import org.hsiaomartin.springbootmall.constant.InformationType;
import org.hsiaomartin.springbootmall.model.Information;

import java.util.List;

public interface InformationService {

    List<Information> getInfosByType(InformationType type);

    Information getInfoById(Integer infoId);
}
