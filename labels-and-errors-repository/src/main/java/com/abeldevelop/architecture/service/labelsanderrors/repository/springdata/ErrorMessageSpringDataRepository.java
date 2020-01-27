package com.abeldevelop.architecture.service.labelsanderrors.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;

public interface ErrorMessageSpringDataRepository extends JpaRepository<ErrorMessageEntity, Long>, JpaSpecificationExecutor<ErrorMessageEntity> {

}