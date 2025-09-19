package org.hsiaomartin.springbootmall.dao;

import org.hsiaomartin.springbootmall.constant.InformationType;
import org.hsiaomartin.springbootmall.model.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationRepository extends JpaRepository<Information, Integer> {

    List<Information> findByType(InformationType type);
}
