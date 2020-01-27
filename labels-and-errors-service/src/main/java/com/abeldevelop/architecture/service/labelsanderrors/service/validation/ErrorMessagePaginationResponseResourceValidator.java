package com.abeldevelop.architecture.service.labelsanderrors.service.validation;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.server.ValidationResponseException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationFactory;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessagePaginationResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.service.config.ErrorCodeProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ErrorMessagePaginationResponseResourceValidator implements ValidationResource {

	private final ErrorCodeProperties errorCodeProperties;
	private final ValidationFactory validationFactory;
	
	@Override
	public boolean areYouTheElement(String elementName) {
		return ErrorMessagePaginationResponseResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		if (toValidate == null) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessagePaginationResponseResourceNotNull());
		}
		ErrorMessagePaginationResponseResource errorMessagePaginationResponseResource = (ErrorMessagePaginationResponseResource) toValidate;
		validatePagination(errorMessagePaginationResponseResource);
		validateErrorMessages(errorMessagePaginationResponseResource);
	}

	private void validatePagination(ErrorMessagePaginationResponseResource errorMessagePaginationResponseResource) {
		validationFactory.validate(errorMessagePaginationResponseResource.getPagination());
	}
	
	private void validateErrorMessages(ErrorMessagePaginationResponseResource errorMessagePaginationResponseResource) {
		if(errorMessagePaginationResponseResource.getErrorMessages() == null) {
			throw new ValidationResponseException(errorCodeProperties.getErrorMessagePaginationResponseResourceErrorMessagesNotNull());
		}
		errorMessagePaginationResponseResource.getErrorMessages().stream().forEach(validationFactory::validate);
	}
	
}
