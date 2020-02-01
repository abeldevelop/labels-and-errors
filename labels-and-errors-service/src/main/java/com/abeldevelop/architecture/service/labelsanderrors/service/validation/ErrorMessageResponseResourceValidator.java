package com.abeldevelop.architecture.service.labelsanderrors.service.validation;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.server.ValidationResponseException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageFieldsSizeConstants;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.service.config.ErrorCodeProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ErrorMessageResponseResourceValidator implements ValidationResource {

	private final ErrorCodeProperties errorCodeProperties;
	
	@Override
	public boolean areYouTheElement(String elementName) {
		return ErrorMessageResponseResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		ErrorMessageResponseResource errorMessageResponseResource = (ErrorMessageResponseResource) toValidate;
		validateId(errorMessageResponseResource);
		validateServiceName(errorMessageResponseResource);
		validateLanguageCode(errorMessageResponseResource);
		validateCode(errorMessageResponseResource);
		validateMessage(errorMessageResponseResource);
		validateVersion(errorMessageResponseResource);
	}

	private void validateId(ErrorMessageResponseResource errorMessageResponseResource) {
		if(errorMessageResponseResource.getId() == null) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageIdNotNull());
		}
	}
	
	private void validateServiceName(ErrorMessageResponseResource errorMessageResponseResource) {
		if(StringUtils.isEmpty(errorMessageResponseResource.getServiceName())) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageServiceNameNotNull());
		}
		if(!StringUtils.isSizeBetween(errorMessageResponseResource.getServiceName(), ErrorMessageFieldsSizeConstants.SERVICE_NAME_MIN_SIZE, ErrorMessageFieldsSizeConstants.SERVICE_NAME_MAX_SIZE)) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageServiceNameSize());
		}
	}
	
	private void validateLanguageCode(ErrorMessageResponseResource errorMessageResponseResource) {
		if(StringUtils.isEmpty(errorMessageResponseResource.getLanguageCode())) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageLanguageCodeNotNull());
		}
		if(!StringUtils.isSizeBetween(errorMessageResponseResource.getLanguageCode(), ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MIN_SIZE, ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MAX_SIZE)) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageLanguageCodeSize());
		}
	}

	private void validateCode(ErrorMessageResponseResource errorMessageResponseResource) {
		if(StringUtils.isEmpty(errorMessageResponseResource.getCode())) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageCodeNotNull());
		}
		if(!StringUtils.isSizeBetween(errorMessageResponseResource.getCode(), ErrorMessageFieldsSizeConstants.CODE_MIN_SIZE, ErrorMessageFieldsSizeConstants.CODE_MAX_SIZE)) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageCodeSize());
		}
	}

	private void validateMessage(ErrorMessageResponseResource errorMessageResponseResource) {
		if(StringUtils.isEmpty(errorMessageResponseResource.getMessage())) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageMessageNotNull());
		}
		if(!StringUtils.isSizeBetween(errorMessageResponseResource.getMessage(), ErrorMessageFieldsSizeConstants.MESSAGE_MIN_SIZE, ErrorMessageFieldsSizeConstants.MESSAGE_MAX_SIZE)) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageMessageSize());
		}
	}
	
	private void validateVersion(ErrorMessageResponseResource errorMessageResponseResource) {
		if(errorMessageResponseResource.getVersion() == null) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessageVersionNotNull());
		}
	}
}
