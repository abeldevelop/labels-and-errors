package com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.library.common.exception.client.NotFoundException;
import com.abeldevelop.architecture.library.common.mapper.pagination.PaginationMapper;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.repository.ErrorMessageRepository;
import com.abeldevelop.architecture.service.labelsanderrors.repository.specification.ErrorMessageSpecification;
import com.abeldevelop.architecture.service.labelsanderrors.service.config.ErrorCodeProperties;
import com.abeldevelop.architecture.service.labelsanderrors.service.domain.ErrorMessage;
import com.abeldevelop.architecture.service.labelsanderrors.service.mapper.ErrorMessageMapper;
import com.abeldevelop.architecture.service.labelsanderrors.service.mapper.ErrorMessageSortMapper;
import com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.FindErrorMessageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindErrorMessageServiceImpl implements FindErrorMessageService {
	
	@Value("${abeldevelop.language.default}")
    private String defaultLanguage;
	
	@Value("${abeldevelop.serice-name.architecture}")
	private String architectureService;
	
	private final ErrorMessageRepository errorMessageRepository;
	private final ErrorCodeProperties errorCodeProperties;
	private final ErrorMessageMapper errorMessageMapper;
	private final ErrorMessageSortMapper errorMessageSortMapper;
    private final PaginationMapper paginationMapper;
	private final ErrorMessageSpecification errorMessageSpecification;
	
	@Override
	public ErrorMessage executeFindById(Long id) {
		Optional<ErrorMessageEntity> errorMessageEntity = errorMessageRepository.executeFindById(id);
    	if(!errorMessageEntity.isPresent()) {
    		throw new NotFoundException(errorCodeProperties.getErrorMessageIdNotExist(), Arrays.asList(id));
    	}
    	return errorMessageMapper.mapEntityToDomain(errorMessageEntity.get());
	}

	@Override
	public ErrorMessage executeFindOne(String serviceName, String languageCode, String code) {
		
		//Find the error message
		Optional<ErrorMessageEntity> errorMessageEntity = findOneByServiceName(serviceName, languageCode, code);
		
		//If not exist search if is architecture error
		if(!errorMessageEntity.isPresent()) {
			errorMessageEntity = findOneByServiceName(architectureService, languageCode, code);
        }
		
        if(!errorMessageEntity.isPresent()) {
            throw new NotFoundException(errorCodeProperties.getErrorMessageWithRequestParametersNotFound(), Arrays.asList(code, serviceName, languageCode));
        }
        
        return errorMessageMapper.mapEntityToDomain(errorMessageEntity.get());
	}
	
	private Optional<ErrorMessageEntity> findOneByServiceName(String serviceName, String languageCode, String code) {
		Optional<ErrorMessageEntity> errorMessageEntity = findOne(serviceName, languageCode, code);
		//Find by the default language
		if(!errorMessageEntity.isPresent()) {
            errorMessageEntity = findOne(serviceName, defaultLanguage, code);
        }
		return errorMessageEntity;
	}

	private Optional<ErrorMessageEntity> findOne(String serviceName, String languageCode, String code) {
        return errorMessageRepository.executeFindOne(
                errorMessageSpecification.serviceNameEquals(serviceName)
                .and(errorMessageSpecification.codeEquals(code))
                .and(errorMessageSpecification.languageCodeEquals(languageCode)));
    }
	
	@Override
	public PaginationResult<ErrorMessage> executeFindAll(PaginationAndSortIn paginationAndSortIn, String serviceName, String languageCode, String code) {
		Page<ErrorMessageEntity> errorMessageEntityPage = findAll(paginationAndSortIn, serviceName, languageCode, code);
		
		return PaginationResult.of(
                paginationMapper.mapPageToPaginationOut(errorMessageEntityPage),
                errorMessageEntityPage.getContent().stream().map(errorMessageMapper::mapEntityToDomain).collect(Collectors.toList()));
	}
	
	private Page<ErrorMessageEntity> findAll(PaginationAndSortIn paginationAndSortIn, String serviceName, String languageCode, String code) {
        PageRequest pageRequest = PageRequest.of(
        		paginationAndSortIn.getPagination().getPage(), 
        		paginationAndSortIn.getPagination().getSize(), 
        		errorMessageSortMapper.map(paginationAndSortIn.getSort()));
        
        Specification<ErrorMessageEntity> specification = getSpecification(serviceName, languageCode, code);
        if(specification == null) {
        	return errorMessageRepository.executeFindAll(pageRequest);
        } else {
        	return errorMessageRepository.executeFindAll(specification, pageRequest);
        }
    }
	
	private Specification<ErrorMessageEntity> getSpecification(String serviceName, String languageCode, String code) {
		List<Specification<ErrorMessageEntity>> specifications = new ArrayList<Specification<ErrorMessageEntity>>();
		if(!StringUtils.isEmpty(serviceName)) {
			specifications.add(errorMessageSpecification.serviceNameLike(serviceName));
		}
		if(!StringUtils.isEmpty(code)) {
			specifications.add(errorMessageSpecification.codeLike(code));
		}
		if(!StringUtils.isEmpty(languageCode)) {
			specifications.add(errorMessageSpecification.languageCodeLike(languageCode));
		}
		if(specifications.isEmpty()) {
			return null;
		}
		Specification<ErrorMessageEntity> specification = null;
		int index = 0;
		for(Specification<ErrorMessageEntity> spec : specifications) {
			if(index == 0) {
				specification = spec;
			}
			specification = specification.or(spec);
			index++;
		}
		return specification;
	}
	
}
