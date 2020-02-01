package com.abeldevelop.architecture.service.labelsanderrors.service.controller.v1;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationAndSortIn;
import com.abeldevelop.architecture.library.common.domain.pagination.in.PaginationIn;
import com.abeldevelop.architecture.library.common.domain.pagination.out.PaginationResult;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationFactory;
import com.abeldevelop.architecture.library.common.mapper.pagination.PaginationMapper;
import com.abeldevelop.architecture.service.labelsanderrors.api.v1.ErrorMessageApi;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.CreateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessagePaginationResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageSort;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.UpdateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.service.domain.ErrorMessage;
import com.abeldevelop.architecture.service.labelsanderrors.service.mapper.ErrorMessageMapper;
import com.abeldevelop.architecture.service.labelsanderrors.service.mapper.ErrorMessageSortMapper;
import com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.CreateErrorMessageService;
import com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.DeleteErrorMessageService;
import com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.FindErrorMessageService;
import com.abeldevelop.architecture.service.labelsanderrors.service.service.v1.UdateErrorMessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ErrorMessageController implements ErrorMessageApi {

	private final ValidationFactory validationFactory;
	private final ErrorMessageMapper errorMessageMapper;
	private final PaginationMapper paginationMapper;
	private final ErrorMessageSortMapper errorMessageSortMapper;
	private final CreateErrorMessageService createErrorMessageService;
	private final UdateErrorMessageService udateErrorMessageService;
	private final DeleteErrorMessageService deleteErrorMessageService;
	private final FindErrorMessageService findErrorMessageService;
	
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
		
		ErrorMessageResponseResource response = errorMessageMapper.mapDomainToResource(
				udateErrorMessageService.executeUdate(
						errorMessageMapper.mapResourceToDomain(id, updateErrorMessageRequestResource)));
		
		validationFactory.validate(response);
		log.info("ErrorMessageController.executeUpdate Data OUT => response: {}", response);
		return response;
	}

	@Override
	public void executeDelete(Long id) {
		log.info("ErrorMessageController.executeDelete Data IN => id: {}", id);
		deleteErrorMessageService.executeDelete(id);
		log.info("ErrorMessageController.executeDelete Data OUT => NO DATA");
	}

	@Override
	public ErrorMessageResponseResource executeFindById(Long id) {
		log.info("ErrorMessageController.executeFindById Data IN => id: {}", id);
		
		ErrorMessageResponseResource response = errorMessageMapper.mapDomainToResource(findErrorMessageService.executeFindById(id));
		
		validationFactory.validate(response);
		log.info("ErrorMessageController.executeFindById Data OUT => response: {}", response);
		return response;
	}

	@Override
	public ErrorMessageResponseResource executeFindOne(String serviceName, String languageCode, String code) {
		log.info("ErrorMessageController.executeFindOne Data IN => serviceName: {}, languageCode: {}, code: {}", serviceName, languageCode, code);
		
		ErrorMessageResponseResource response = errorMessageMapper.mapDomainToResource(findErrorMessageService.executeFindOne(serviceName, languageCode, code));
		
		validationFactory.validate(response);
		log.info("ErrorMessageController.executeFindOne Data OUT => response: {}", response);
		return response;
	}

	@Override
	public ErrorMessagePaginationResponseResource executeFindAll(Integer page, Integer size, ErrorMessageSort sort, String serviceName, String languageCode, String code) {
		log.info("ErrorMessageController.executeFindAll Data IN => page: {}, size: {}, sort: {}, serviceName: {}, languageCode: {}, code: {}", page, size, sort, serviceName, languageCode, code);
		PaginationIn paginationIn = paginationMapper.map(page, size);
		PaginationAndSortIn paginationAndSortIn = PaginationAndSortIn.builder()
				.pagination(paginationIn)
				.sort(errorMessageSortMapper.map(sort))
				.build();
		
		PaginationResult<ErrorMessage> paginationResult = findErrorMessageService.executeFindAll(paginationAndSortIn, serviceName, languageCode, code);
		
		ErrorMessagePaginationResponseResource response = ErrorMessagePaginationResponseResource.builder()
				.pagination(paginationMapper.map(paginationResult.getPagination()))
				.errorMessages(paginationResult.getResults().stream().map(errorMessageMapper::mapDomainToResource).collect(Collectors.toList()))
				.build();
		
		validationFactory.validate(response);
		log.info("ErrorMessageController.executeFindAll Data OUT => response: {}", response);
		return response;
	}

}
