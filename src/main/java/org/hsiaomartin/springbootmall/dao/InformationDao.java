package org.hsiaomartin.springbootmall.dao;

import org.hsiaomartin.springbootmall.constant.InformationType;
import org.hsiaomartin.springbootmall.model.Information;

import java.util.List;

public interface InformationDao {

    List<Information> getInfosByType(InformationType type);

    Information getInfoById(Integer infoId);
}
