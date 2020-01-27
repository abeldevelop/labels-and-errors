package com.abeldevelop.architecture.service.labelsanderrors.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity_;

@Component
public class ErrorMessageSpecification {

    public Specification<ErrorMessageEntity> serviceNameEquals(String serviceName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.upper(root.get(ErrorMessageEntity_.serviceName)), serviceName.toUpperCase());
    }
    
    public Specification<ErrorMessageEntity> serviceNameLike(String serviceName) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(ErrorMessageEntity_.serviceName)), "%" + serviceName.toUpperCase() + "%");
    }
    
    public Specification<ErrorMessageEntity> languageCodeEquals(String languageCode) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.upper(root.get(ErrorMessageEntity_.languageCode)), languageCode.toUpperCase());
    }

    public Specification<ErrorMessageEntity> languageCodeLike(String languageCode) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(ErrorMessageEntity_.languageCode)), "%" + languageCode.toUpperCase() + "%");
    }
    
    public Specification<ErrorMessageEntity> codeEquals(String code) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.upper(root.get(ErrorMessageEntity_.code)), code.toUpperCase());
    }
    
    public Specification<ErrorMessageEntity> codeLike(String code) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get(ErrorMessageEntity_.code)), "%" + code.toUpperCase() + "%");
    }
    
}