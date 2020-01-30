package com.abeldevelop.architecture.service.labelsanderrors.service.validation;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.library.common.exception.client.ValidationRequestException;
import com.abeldevelop.architecture.library.common.factory.validation.ValidationResource;
import com.abeldevelop.architecture.library.common.util.StringUtils;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageFieldsSizeConstants;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.UpdateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.service.config.ErrorCodeProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateErrorMessageRequestResourceValidator implements ValidationResource {

	private final ErrorCodeProperties errorCodeProperties;
	
	@Override
	public boolean areYouTheElement(String elementName) {
		return UpdateErrorMessageRequestResource.class.getCanonicalName().equals(elementName);
	}

	@Override
	public void validate(Object toValidate) {
		if (toValidate == null) {
			throw new ValidationRequestException(errorCodeProperties.getUpdateErrorMessageRequestResourceNotNull());
		}
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = (UpdateErrorMessageRequestResource) toValidate;
		validateServiceName(updateErrorMessageRequestResource);
		validateLanguageCode(updateErrorMessageRequestResource);
		validateCode(updateErrorMessageRequestResource);
		validateMessage(updateErrorMessageRequestResource);
		validateVersion(updateErrorMessageRequestResource);
	}

	private void validateServiceName(UpdateErrorMessageRequestResource updateErrorMessageRequestResource) {
		if(StringUtils.isEmpty(updateErrorMessageRequestResource.getServiceName())) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageServiceNameNotNull());
		}
		if(!StringUtils.isSizeBetween(updateErrorMessageRequestResource.getServiceName(), ErrorMessageFieldsSizeConstants.SERVICE_NAME_MIN_SIZE, ErrorMessageFieldsSizeConstants.SERVICE_NAME_MAX_SIZE)) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageServiceNameSize());
		}
	}
	
	private void validateLanguageCode(UpdateErrorMessageRequestResource updateErrorMessageRequestResource) {
		if(StringUtils.isEmpty(updateErrorMessageRequestResource.getLanguageCode())) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageLanguageCodeNotNull());
		}
		if(!StringUtils.isSizeBetween(updateErrorMessageRequestResource.getLanguageCode(), ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MIN_SIZE, ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MAX_SIZE)) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageLanguageCodeSize());
		}
	}

	private void validateCode(UpdateErrorMessageRequestResource updateErrorMessageRequestResource) {
		if(StringUtils.isEmpty(updateErrorMessageRequestResource.getCode())) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageCodeNotNull());
		}
		if(!StringUtils.isSizeBetween(updateErrorMessageRequestResource.getCode(), ErrorMessageFieldsSizeConstants.CODE_MIN_SIZE, ErrorMessageFieldsSizeConstants.CODE_MAX_SIZE)) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageCodeSize());
		}
	}

	private void validateMessage(UpdateErrorMessageRequestResource updateErrorMessageRequestResource) {
		if(StringUtils.isEmpty(updateErrorMessageRequestResource.getMessage())) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageMessageNotNull());
		}
		if(!StringUtils.isSizeBetween(updateErrorMessageRequestResource.getMessage(), ErrorMessageFieldsSizeConstants.MESSAGE_MIN_SIZE, ErrorMessageFieldsSizeConstants.MESSAGE_MAX_SIZE)) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageMessageSize());
		}
	}
	
	private void validateVersion(UpdateErrorMessageRequestResource updateErrorMessageRequestResource) {
		if(updateErrorMessageRequestResource.getVersion() == null) {
			throw new ValidationRequestException(errorCodeProperties.getErrorMessageVersionNotNull());
		}
	}
}