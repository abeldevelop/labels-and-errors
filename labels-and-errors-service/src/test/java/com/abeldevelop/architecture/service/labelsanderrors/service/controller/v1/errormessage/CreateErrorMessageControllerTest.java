package com.abeldevelop.architecture.service.labelsanderrors.service.controller.v1.errormessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.architecture.library.common.dto.exception.ErrorResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.CreateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.repository.springdata.ErrorMessageSpringDataRepository;
import com.abeldevelop.architecture.service.labelsanderrors.service.controller.CommonTestController;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class CreateErrorMessageControllerTest extends CommonTestController {

	private static final String BASE_V1_ENDPOINT = "/v1/error-message";
	
	private static final String SERVICE_NAME_OK_VALUE = "serviceName";
	private static final String LANGUAGE_CODE_OK_VALUE = "es-ES";
	private static final String CODE_OK_VALUE = "categoryNameNotNull";
	private static final String MESSAGE_OK_VALUE = "The category name can not be null";
	
	@Autowired
	private ErrorMessageSpringDataRepository errorMessageSpringDataRepository;
	
	@Test
	public void test_createErrorMessage_ko_nameNotNull() throws Exception {
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource(null, null, null, null);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageServiceNameNotNull", response.getMessage());
	}
	
	@Test
	public void test_createErrorMessage_ko_nameSize() throws Exception {
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource("a", null, null, null);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageServiceNameSize", response.getMessage());
	}
	
	@Test
	public void test_createErrorMessage_ko_languageCodeNotNull() throws Exception {
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, null, null, null);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageLanguageCodeNotNull", response.getMessage());
	}
	
	@Test
	public void test_createErrorMessage_ko_languageCodeSize() throws Exception {
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, "a", null, null);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageLanguageCodeSize", response.getMessage());
	}
	
	@Test
	public void test_createErrorMessage_ko_codeNotNull() throws Exception {
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, null, null);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageCodeNotNull", response.getMessage());
	}
	
	@Test
	public void test_createErrorMessage_ko_codeSize() throws Exception {
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, "a", null);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageCodeSize", response.getMessage());
	}
	
	@Test
	public void test_createErrorMessage_ko_messageNotNull() throws Exception {
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, null);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageMessageNotNull", response.getMessage());
	}
	
	@Test
	public void test_createErrorMessage_ko_messageSize() throws Exception {
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, "a");
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageMessageSize", response.getMessage());
	}
	
	@Test
	public void test_createErrorMessage_ko_alreadyExists() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		errorMessageSpringDataRepository.save(ErrorMessageEntity.builder().serviceName(SERVICE_NAME_OK_VALUE)
				.languageCode(LANGUAGE_CODE_OK_VALUE)
				.code(CODE_OK_VALUE)
				.message(MESSAGE_OK_VALUE).build());
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, MESSAGE_OK_VALUE);
		ErrorResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageWithServiceNameAndCodeAndLanguageCodeExist", response.getMessage());
	}
	
	@Test
	public void test_createErrorMessage_ok() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		CreateErrorMessageRequestResource createErrorMessageRequestResource = createErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, MESSAGE_OK_VALUE);
		ErrorMessageResponseResource response = callPostEndpoint(BASE_V1_ENDPOINT, createErrorMessageRequestResource, HttpStatus.CREATED.value(), ErrorMessageResponseResource.class);
		
		assertNotNull(response.getId());
		assertEquals(SERVICE_NAME_OK_VALUE, response.getServiceName());
		assertEquals(LANGUAGE_CODE_OK_VALUE, response.getLanguageCode());
	    assertEquals(CODE_OK_VALUE, response.getCode());
	    assertEquals(MESSAGE_OK_VALUE, response.getMessage());
	    assertNotNull(response.getVersion());
	}
	
	private CreateErrorMessageRequestResource createErrorMessageRequestResource(String serviceName, String languageCode, String code, String message) {
		return CreateErrorMessageRequestResource.builder()
				.serviceName(serviceName)
				.languageCode(languageCode)
				.code(code)
				.message(message)
				.build();
	}
}