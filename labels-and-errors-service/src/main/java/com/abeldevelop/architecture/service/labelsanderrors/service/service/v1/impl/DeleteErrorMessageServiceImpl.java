package com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abeldevelop.architecture.library.common.exception.client.NotFoundException;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.repository.ErrorMessageRepository;
import com.abeldevelop.architecture.service.labelsanderrors.service.config.ErrorCodeProperties;
import com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.DeleteErrorMessageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteErrorMessageServiceImpl implements DeleteErrorMessageService {
	
	private final ErrorMessageRepository errorMessageRepository;
	private final ErrorCodeProperties errorCodeProperties;
	
	@Override
	public void executeDelete(Long id) {
		//TODO => Validate the permisions
		checkIfErrorMessageExist(id);
		errorMessageRepository.executeDeleteById(id);
	}

	private void checkIfErrorMessageExist(Long id) {
    	Optional<ErrorMessageEntity> errorMessageEntity = errorMessageRepository.executeFindById(id);
    	if(!errorMessageEntity.isPresent()) {
    		throw new NotFoundException(errorCodeProperties.getErrorMessageIdNotExist(), Arrays.asList(id));
    	}
    }
}
