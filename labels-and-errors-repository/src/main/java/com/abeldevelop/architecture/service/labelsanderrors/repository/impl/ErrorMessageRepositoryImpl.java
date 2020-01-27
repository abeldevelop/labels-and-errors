package com.abeldevelop.architecture.service.labelsanderrors.repository.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.repository.ErrorMessageRepository;
import com.abeldevelop.architecture.service.labelsanderrors.repository.springdata.ErrorMessageSpringDataRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ErrorMessageRepositoryImpl implements ErrorMessageRepository {
    
    private final ErrorMessageSpringDataRepository errorMessageSpringDataRepository;

    @Override
    public ErrorMessageEntity executeSave(ErrorMessageEntity errorMessageEntity) {
        return errorMessageSpringDataRepository.save(errorMessageEntity);
    }

    @Override
    public Optional<ErrorMessageEntity> executeFindById(Long id) {
        return errorMessageSpringDataRepository.findById(id);
    }
    
    @Override
    public Optional<ErrorMessageEntity> executeFindOne(Specification<ErrorMessageEntity> spec) {
        return errorMessageSpringDataRepository.findOne(spec);
    }

    @Override
    public void executeDeleteById(Long id) {
        errorMessageSpringDataRepository.deleteById(id);
        
    }

    @Override
    public Page<ErrorMessageEntity> executeFindAll(Pageable pageable) {
        return errorMessageSpringDataRepository.findAll(pageable);
    }

    @Override
    public Page<ErrorMessageEntity> executeFindAll(Specification<ErrorMessageEntity> spec, Pageable pageable) {
        return errorMessageSpringDataRepository.findAll(spec, pageable);
    }

}