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
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.UpdateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.repository.springdata.ErrorMessageSpringDataRepository;
import com.abeldevelop.architecture.service.labelsanderrors.service.controller.CommonTestController;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UdateErrorMessageControllerTest extends CommonTestController {

	private static final String BASE_V1_ENDPOINT = "/v1/error-message";
	
	private static final String SERVICE_NAME_OK_VALUE = "serviceName";
	private static final String LANGUAGE_CODE_OK_VALUE = "es-ES";
	private static final String CODE_OK_VALUE = "categoryNameNotNull";
	private static final String MESSAGE_OK_VALUE = "The category name can not be null";
	private static final Integer VERSION_OK_VALUE = 0;
	
	@Autowired
	private ErrorMessageSpringDataRepository errorMessageSpringDataRepository;
	
	@Test
	public void test_updateErrorMessage_ko_nameNotNull() throws Exception {
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(null, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, MESSAGE_OK_VALUE, VERSION_OK_VALUE);
		Long errorMessageId = 1L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageServiceNameNotNull", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_nameSize() throws Exception {
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource("a", LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, MESSAGE_OK_VALUE, VERSION_OK_VALUE);
		Long errorMessageId = 1L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageServiceNameSize", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_languageCodeNotNull() throws Exception {
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, null, CODE_OK_VALUE, MESSAGE_OK_VALUE, VERSION_OK_VALUE);
		Long errorMessageId = 1L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageLanguageCodeNotNull", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_languageCodeSize() throws Exception {
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, "a", CODE_OK_VALUE, MESSAGE_OK_VALUE, VERSION_OK_VALUE);
		Long errorMessageId = 1L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageLanguageCodeSize", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_codeNotNull() throws Exception {
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, null, MESSAGE_OK_VALUE, VERSION_OK_VALUE);
		Long errorMessageId = 1L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageCodeNotNull", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_codeSize() throws Exception {
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, "a", MESSAGE_OK_VALUE, VERSION_OK_VALUE);		
		Long errorMessageId = 1L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageCodeSize", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_messageNotNull() throws Exception {
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, null, VERSION_OK_VALUE);
		Long errorMessageId = 1L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageMessageNotNull", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_messageSize() throws Exception {
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, "a", VERSION_OK_VALUE);
		Long errorMessageId = 1L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageMessageSize", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_versionNotNull() throws Exception {
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, MESSAGE_OK_VALUE, null);
		Long errorMessageId = 1L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageVersionNotNull", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_alreadyExists() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		ErrorMessageEntity errorMessageEntitySaved = errorMessageSpringDataRepository.save(ErrorMessageEntity.builder().serviceName(SERVICE_NAME_OK_VALUE).languageCode(LANGUAGE_CODE_OK_VALUE).code(CODE_OK_VALUE).message(MESSAGE_OK_VALUE).build());		
		
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, MESSAGE_OK_VALUE, VERSION_OK_VALUE);
		Long errorMessageId = errorMessageEntitySaved.getId();
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.BAD_REQUEST.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageWithServiceNameAndCodeAndLanguageCodeExist", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ko_notExists() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		errorMessageSpringDataRepository.save(ErrorMessageEntity.builder().serviceName(SERVICE_NAME_OK_VALUE).languageCode(LANGUAGE_CODE_OK_VALUE).code(CODE_OK_VALUE).message(MESSAGE_OK_VALUE).build());
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, MESSAGE_OK_VALUE, VERSION_OK_VALUE);
		Long errorMessageId = 99L;
		ErrorResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.NOT_FOUND.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageIdNotExist", response.getMessage());
	}
	
	@Test
	public void test_updateErrorMessage_ok() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		UpdateErrorMessageRequestResource updateErrorMessageRequestResource = updateErrorMessageRequestResource(SERVICE_NAME_OK_VALUE, LANGUAGE_CODE_OK_VALUE, CODE_OK_VALUE, MESSAGE_OK_VALUE, VERSION_OK_VALUE);
		ErrorMessageEntity errorMessageEntitySaved = errorMessageSpringDataRepository.save(ErrorMessageEntity.builder().serviceName(SERVICE_NAME_OK_VALUE + "2").languageCode(LANGUAGE_CODE_OK_VALUE).code(CODE_OK_VALUE).message(MESSAGE_OK_VALUE).build());
		Long errorMessageId = errorMessageEntitySaved.getId();
		ErrorMessageResponseResource response = callPutEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, updateErrorMessageRequestResource, HttpStatus.OK.value(), ErrorMessageResponseResource.class);
		
		assertNotNull(response.getId());
		assertEquals(SERVICE_NAME_OK_VALUE, response.getServiceName());
		assertEquals(LANGUAGE_CODE_OK_VALUE, response.getLanguageCode());
	    assertEquals(CODE_OK_VALUE, response.getCode());
	    assertEquals(MESSAGE_OK_VALUE, response.getMessage());
	    assertNotNull(response.getVersion());
	}
	
	private UpdateErrorMessageRequestResource updateErrorMessageRequestResource(String serviceName, String languageCode, String code, String message, Integer version) {
		return UpdateErrorMessageRequestResource.builder()
				.serviceName(serviceName)
				.languageCode(languageCode)
				.code(code)
				.message(message)
				.version(version)
				.build();
	}
}
