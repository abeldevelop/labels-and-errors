package com.abeldevelop.architecture.service.labelsanderrors.service.validation;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.client.ValidationRequestException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.CreateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageFieldsSizeConstants;
import com.abeldevelop.architecture.service.labelsanderrors.service.config.ErrorCodeProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CreateErrorMessageRequestResourceValidator implements ValidationResource {

	private final ErrorCodeProperties errorCodeProperties;

	@Override
	public boolean areYouTheElement(String elementName) {
		return CreateErrorMessageRequestResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		CreateErrorMessageRequestResource createErrorMessageRequestResource = (CreateErrorMessageRequestResource) toValidate;
		validateServiceName(createErrorMessageRequestResource);
		validateLanguageCode(createErrorMessageRequestResource);
		validateCode(createErrorMessageRequestResource);
		validateMessage(createErrorMessageRequestResource);
	}

	private void validateServiceName(CreateErrorMessageRequestResource createErrorMessageRequestResource) {
		if(StringUtils.isEmpty(createErrorMessageRequestResource.getServiceName())) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageServiceNameNotNull());
		}
		if(!StringUtils.isSizeBetween(createErrorMessageRequestResource.getServiceName(), ErrorMessageFieldsSizeConstants.SERVICE_NAME_MIN_SIZE, ErrorMessageFieldsSizeConstants.SERVICE_NAME_MAX_SIZE)) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageServiceNameSize(), Arrays.asList(ErrorMessageFieldsSizeConstants.SERVICE_NAME_MIN_SIZE, ErrorMessageFieldsSizeConstants.SERVICE_NAME_MAX_SIZE));
		}
	}

	private void validateLanguageCode(CreateErrorMessageRequestResource createErrorMessageRequestResource) {
		if(StringUtils.isEmpty(createErrorMessageRequestResource.getLanguageCode())) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageLanguageCodeNotNull());
		}
		if(!StringUtils.isSizeBetween(createErrorMessageRequestResource.getLanguageCode(), ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MIN_SIZE, ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MAX_SIZE)) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageLanguageCodeSize(), Arrays.asList(ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MIN_SIZE, ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MAX_SIZE));
		}
	}

	private void validateCode(CreateErrorMessageRequestResource createErrorMessageRequestResource) {
		if(StringUtils.isEmpty(createErrorMessageRequestResource.getCode())) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageCodeNotNull());
		}
		if(!StringUtils.isSizeBetween(createErrorMessageRequestResource.getCode(), ErrorMessageFieldsSizeConstants.CODE_MIN_SIZE, ErrorMessageFieldsSizeConstants.CODE_MAX_SIZE)) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageCodeSize(), Arrays.asList(ErrorMessageFieldsSizeConstants.CODE_MIN_SIZE, ErrorMessageFieldsSizeConstants.CODE_MAX_SIZE));
		}
	}

	private void validateMessage(CreateErrorMessageRequestResource createErrorMessageRequestResource) {
		if(StringUtils.isEmpty(createErrorMessageRequestResource.getMessage())) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageMessageNotNull());
		}
		if(!StringUtils.isSizeBetween(createErrorMessageRequestResource.getMessage(), ErrorMessageFieldsSizeConstants.MESSAGE_MIN_SIZE, ErrorMessageFieldsSizeConstants.MESSAGE_MAX_SIZE)) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageMessageSize(), Arrays.asList(ErrorMessageFieldsSizeConstants.MESSAGE_MIN_SIZE, ErrorMessageFieldsSizeConstants.MESSAGE_MAX_SIZE));
		}
	}
}
