package com.abeldevelop.architecture.service.labelsanderrors.service.controller.v1;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.architecture.library.common.factory.validation.ValidationFactory;
import com.abeldevelop.architecture.service.labelsanderrors.api.v1.ErrorMessageApi;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.CreateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessagePaginationResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageSort;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.UpdateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.service.mapper.ErrorMessageMapper;
import com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.CreateErrorMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ErrorMessageController implements ErrorMessageApi {

	private final ValidationFactory validationFactory;
	private final ErrorMessageMapper errorMessageMapper;
	private final CreateErrorMessageService createErrorMessageService;
	
	@Override
	public ErrorMessageResponseResource executeCreate(CreateErrorMessageRequestResource createErrorMessageRequestResource) {
		log.info("ErrorMessageController.executeCreate Data IN => createErrorMessageRequestResource: {}", createErrorMessageRequestResource);
		validationFactory.validate(createErrorMessageRequestResource);
		
		ErrorMessageResponseResource response = errorMessageMapper.mapDomainToResource(
				createErrorMessageService.executeCreate(
						errorMessageMapper.mapResourceToDomain(createErrorMessageRequestResource)));
		
		validationFactory.validate(response);
		log.info("ErrorMessageController.executeCreate Data OUT => response: {}", response);
		return response;
	}

	@Override
	public ErrorMessageResponseResource executeUpdate(Long id, UpdateErrorMessageRequestResource updateErrorMessageRequestResource) {
		log.info("ErrorMessageController.executeUpdate Data IN => updateErrorMessageRequestResource: {}", updateErrorMessageRequestResource);
		validationFactory.validate(updateErrorMessageRequestResource);
		
		ErrorMessageResponseResource response = null; //TODO Call service
		
		validationFactory.validate(response);
		log.info("ErrorMessageController.executeUpdate Data OUT => response: {}", response);
		return response;
	}

	@Override
	public void executeDelete(Long id) {
		log.info("ErrorMessageController.executeDelete Data IN => id: {}", id);
		//TODO Call service
		log.info("ErrorMessageController.executeDelete Data OUT => NO DATA");
	}

	@Override
	public ErrorMessageResponseResource executeFindById(Long id) {
		log.info("ErrorMessageController.executeFindById Data IN => id: {}", id);
		
		ErrorMessageResponseResource response = null; //TODO Call service
		
		validationFactory.validate(response);
		log.info("ErrorMessageController.executeFindById Data OUT => response: {}", response);
		return response;
	}

	@Override
	public ErrorMessageResponseResource executeFindOne(String serviceName, String languageCode, String code) {
		log.info("ErrorMessageController.executeFindOne Data IN => serviceName: {}, languageCode: {}, code: {}", serviceName, languageCode, code);
		
		ErrorMessageResponseResource response = null; //TODO Call service
		
		validationFactory.validate(response);
		log.info("ErrorMessageController.executeFindOne Data OUT => response: {}", response);
		return response;
	}

	@Override
	public ErrorMessagePaginationResponseResource executeFindAll(Integer page, Integer size, ErrorMessageSort sort, String serviceName, String languageCode, String code) {
		log.info("ErrorMessageController.executeFindAll Data IN => page: {}, size: {}, sort: {}, serviceName: {}, languageCode: {}, code: {}", page, size, sort, serviceName, languageCode, code);
		
		ErrorMessagePaginationResponseResource response = null; //TODO Call service
		
		validationFactory.validate(response);
		log.info("ErrorMessageController.executeFindAll Data OUT => response: {}", response);
		return response;
	}

}
