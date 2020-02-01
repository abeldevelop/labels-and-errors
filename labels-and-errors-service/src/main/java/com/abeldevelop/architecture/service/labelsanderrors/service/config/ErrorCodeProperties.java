package com.abeldevelop.architecture.service.labelsanderrors.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties("labels-and-errors-error-code")
public class ErrorCodeProperties {

	private String errorMessageIdNotNull;
	private String errorMessageServiceNameNotNull;
	private String errorMessageServiceNameSize;
	private String errorMessageLanguageCodeNotNull;
	private String errorMessageLanguageCodeSize;
	private String errorMessageCodeNotNull;
	private String errorMessageCodeSize;
	private String errorMessageMessageNotNull;
	private String errorMessageMessageSize;
	private String errorMessageVersionNotNull;
	private String errorMessagePaginationResponseResourceErrorMessagesNotNull;
	
	
	private String errorMessageWithServiceNameAndCodeAndLanguageCodeExist;
	private String errorMessageIdNotExist;
	private String errorMessageWithRequestParametersNotFound;

}