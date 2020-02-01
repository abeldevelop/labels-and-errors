package com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abeldevelop.architecture.library.common.exception.client.BadRequestException;
import com.abeldevelop.architecture.library.common.exception.client.NotFoundException;
import com.abeldevelop.architecture.library.common.service.CommonService;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.repository.ErrorMessageRepository;
import com.abeldevelop.architecture.service.labelsanderrors.repository.specification.ErrorMessageSpecification;
import com.abeldevelop.architecture.service.labelsanderrors.service.config.ErrorCodeProperties;
import com.abeldevelop.architecture.service.labelsanderrors.service.domain.ErrorMessage;
import com.abeldevelop.architecture.service.labelsanderrors.service.mapper.ErrorMessageMapper;
import com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.UdateErrorMessageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UdateErrorMessageServiceImpl extends CommonService implements UdateErrorMessageService {

	private final ErrorMessageRepository errorMessageRepository;
    private final ErrorMessageMapper errorMessageMapper;
    private final ErrorMessageSpecification errorMessageSpecification;
    private final ErrorCodeProperties errorCodeProperties;

    
    @Override
	public ErrorMessage executeUdate(ErrorMessage errorMessage) {
    	//TODO => Validate the permisions
    	
    	ErrorMessageEntity errorMessageEntity = checkIfErrorMessageExist(errorMessage);
    	checkIfNewErrorMessageExist(errorMessage);
    	checkNotExistConflict(errorMessageEntity.getVersion(), errorMessage.getVersion());
    	
    	return errorMessageMapper.mapEntityToDomain(
				errorMessageRepository.executeSave(
						errorMessageMapper.mapDomainToEntity(errorMessage, errorMessageEntity)));
	}
    
    private ErrorMessageEntity checkIfErrorMessageExist(ErrorMessage errorMessage) {
    	Optional<ErrorMessageEntity> errorMessageEntity = errorMessageRepository.executeFindById(errorMessage.getId());
        if(!errorMessageEntity.isPresent()) {
            throw new NotFoundException(errorCodeProperties.getErrorMessageIdNotExist(), Arrays.asList(errorMessage.getId()));
        }
        return errorMessageEntity.get();
    }
    
    private void checkIfNewErrorMessageExist(ErrorMessage errorMessage) {
        Optional<ErrorMessageEntity> errorMessageEntity = errorMessageRepository.executeFindOne(
                errorMessageSpecification.codeEquals(errorMessage.getCode())
                .and(errorMessageSpecification.languageCodeEquals(errorMessage.getLanguageCode()))
                .and(errorMessageSpecification.serviceNameEquals((errorMessage.getServiceName()))));
        if(errorMessageEntity.isPresent()) {
            throw new BadRequestException(errorCodeProperties.getErrorMessageWithServiceNameAndCodeAndLanguageCodeExist(), Arrays.asList(errorMessage.getCode(), errorMessage.getServiceName(), errorMessage.getLanguageCode()));
        }
    }
}
