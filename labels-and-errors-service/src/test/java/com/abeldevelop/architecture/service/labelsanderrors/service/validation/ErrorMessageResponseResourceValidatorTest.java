package com.abeldevelop.architecture.service.labelsanderrors.service.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abeldevelop.architecture.library.common.exception.server.ValidationResponseException;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.service.config.ErrorCodeProperties;

public class ErrorMessageResponseResourceValidatorTest {

	private ErrorMessageResponseResourceValidator errorMessageResponseResourceValidator;
	
	@BeforeEach
	public void setUp() {
		ErrorCodeProperties errorCodeProperties = new ErrorCodeProperties();
		errorMessageResponseResourceValidator = new ErrorMessageResponseResourceValidator(errorCodeProperties);
	}
	
	@Test
	public void test_validateId_ko() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(null)
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
	
	@Test
	public void test_validateServiceName_ko_notNull() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(1L)
				.serviceName(null)
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
	
	@Test
	public void test_validateServiceName_ko_size() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(1L)
				.serviceName("a")
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
	
	@Test
	public void test_validateLanguageCode_ko_notNull() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(1L)
				.serviceName("aaaaa")
				.languageCode(null)
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
	
	@Test
	public void test_validateLanguageCode_ko_notSize() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(1L)
				.serviceName("aaaaa")
				.languageCode("a")
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
	
	@Test
	public void test_validateCode_ko_notNull() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(1L)
				.serviceName("aaaaa")
				.languageCode("aaa")
				.code(null)
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
	
	@Test
	public void test_validateCode_ko_notSize() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(1L)
				.serviceName("aaaaa")
				.languageCode("aaa")
				.code("a")
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
	
	@Test
	public void test_validateMessage_ko_notNull() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(1L)
				.serviceName("aaaaa")
				.languageCode("aaaa")
				.code("aaaaa")
				.message(null)
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
	
	@Test
	public void test_validateMessage_ko_notSize() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(1L)
				.serviceName("aaaaa")
				.languageCode("aaaa")
				.code("aaaaa")
				.message("a")
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
	
	@Test
	public void test_validateVersion_ko() {
		ErrorMessageResponseResource errorMessageResponseResource = ErrorMessageResponseResource.builder()
				.id(1L)
				.serviceName("aaaaa")
				.languageCode("aaa")
				.code("aaaaa")
				.message("aaaaaaaa")
				.version(null)
				.build();
		assertThrows(ValidationResponseException.class, () -> errorMessageResponseResourceValidator.validate(errorMessageResponseResource));
	}
}
