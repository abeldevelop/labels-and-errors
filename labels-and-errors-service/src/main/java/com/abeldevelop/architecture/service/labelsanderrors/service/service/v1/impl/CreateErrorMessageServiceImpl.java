package com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abeldevelop.architecture.library.common.exception.client.BadRequestException;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.repository.ErrorMessageRepository;
import com.abeldevelop.architecture.service.labelsanderrors.repository.specification.ErrorMessageSpecification;
import com.abeldevelop.architecture.service.labelsanderrors.service.config.ErrorCodeProperties;
import com.abeldevelop.architecture.service.labelsanderrors.service.domain.ErrorMessage;
import com.abeldevelop.architecture.service.labelsanderrors.service.mapper.ErrorMessageMapper;
import com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.CreateErrorMessageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateErrorMessageServiceImpl implements CreateErrorMessageService {

	private final ErrorMessageRepository errorMessageRepository;
    private final ErrorMessageMapper errorMessageMapper;
    private final ErrorMessageSpecification errorMessageSpecification;
    private final ErrorCodeProperties errorCodeProperties;
	
	
	@Override
	@Transactional
	public ErrorMessage executeCreate(ErrorMessage errorMessage) {

		//TODO => Validate the permisions
		
		checkIfErrorMessageExist(errorMessage);
		
		return errorMessageMapper.mapEntityToDomain(
				errorMessageRepository.executeSave(
						errorMessageMapper.mapDomainToEntity(errorMessage)));
	}

	
	private void checkIfErrorMessageExist(ErrorMessage errorMessage) {
        Optional<ErrorMessageEntity> errorMessageEntity = errorMessageRepository.executeFindOne(
                errorMessageSpecification.codeEquals(errorMessage.getCode())
                .and(errorMessageSpecification.languageCodeEquals(errorMessage.getLanguageCode()))
                .and(errorMessageSpecification.serviceNameEquals((errorMessage.getServiceName()))));
        if(errorMessageEntity.isPresent()) {
            throw new BadRequestException(errorCodeProperties.getErrorMessageWithServiceNameAndCodeAndLanguageCodeExist(), Arrays.asList(errorMessage.getCode(), errorMessage.getServiceName(), errorMessage.getLanguageCode()));
        }
    }
}
