package com.base.hospital.dao;

import com.base.hospital.bean.PatientEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientDao extends JpaRepository<PatientEntity, Long> {

    PatientEntity save(PatientEntity transactionEntity);

    PatientEntity findOneById(Long id);

    @Override
    List<PatientEntity> findAll(Sort sort);

}
