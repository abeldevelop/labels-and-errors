package com.abeldevelop.architecture.service.labelsanderrors.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;

public interface ErrorMessageRepository {

    public ErrorMessageEntity executeSave(ErrorMessageEntity errorMessageEntity);
    
    public Optional<ErrorMessageEntity> executeFindById(Long id);
    
    public Optional<ErrorMessageEntity> executeFindOne(Specification<ErrorMessageEntity> spec);
    
    public void executeDeleteById(Long id);

    public Page<ErrorMessageEntity> executeFindAll(Pageable pageable);
    
    public Page<ErrorMessageEntity> executeFindAll(Specification<ErrorMessageEntity> spec, Pageable pageable);
    
}