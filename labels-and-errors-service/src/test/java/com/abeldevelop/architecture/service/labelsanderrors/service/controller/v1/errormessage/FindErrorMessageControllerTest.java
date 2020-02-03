package com.abeldevelop.architecture.service.labelsanderrors.service.controller.v1.errormessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.architecture.library.common.dto.exception.ErrorResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessagePaginationResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.repository.springdata.ErrorMessageSpringDataRepository;
import com.abeldevelop.architecture.service.labelsanderrors.service.controller.CommonTestController;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class FindErrorMessageControllerTest extends CommonTestController {

	private static final String BASE_V1_ENDPOINT = "/v1/error-message";
	
	private static final String SERVICE_NAME_OK_VALUE = "serviceName";
	private static final String LANGUAGE_CODE_OK_VALUE = "es-ES";
	private static final String CODE_OK_VALUE = "categoryNameNotNull";
	private static final String MESSAGE_OK_VALUE = "The category name can not be null";
	
	@Autowired
	private ErrorMessageSpringDataRepository errorMessageSpringDataRepository;
	
	@Value("${abeldevelop.used-libraries}")
	private List<String> usedLibraries;
	
	@Test
	public void test_findErrorMessageById_ko_notFound() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		Long errorMessageId = 1L;
		
		ErrorResponseResource response = callGetEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, HttpStatus.NOT_FOUND.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageIdNotExist", response.getMessage());
	}
	
	@Test
	public void test_findErrorMessageById_ok() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		ErrorMessageEntity errorMessageEntity = errorMessageSpringDataRepository.save(ErrorMessageEntity.builder().serviceName(SERVICE_NAME_OK_VALUE).languageCode(LANGUAGE_CODE_OK_VALUE).code(CODE_OK_VALUE).message(MESSAGE_OK_VALUE).build());
		Long errorMessageId = errorMessageEntity.getId();
		
		ErrorMessageResponseResource response = callGetEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, HttpStatus.OK.value(), ErrorMessageResponseResource.class);
		
		assertEquals(errorMessageId, response.getId());
		assertEquals(SERVICE_NAME_OK_VALUE, response.getServiceName());
		assertEquals(LANGUAGE_CODE_OK_VALUE, response.getLanguageCode());
	    assertEquals(CODE_OK_VALUE, response.getCode());
	    assertEquals(MESSAGE_OK_VALUE, response.getMessage());
	    assertNotNull(response.getVersion());
	}
	
	@Test
	public void test_findOneErrorMessage_ok() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		ErrorMessageEntity errorMessageEntity = errorMessageSpringDataRepository.save(ErrorMessageEntity.builder().serviceName(SERVICE_NAME_OK_VALUE).languageCode(LANGUAGE_CODE_OK_VALUE).code(CODE_OK_VALUE).message(MESSAGE_OK_VALUE).build());
		Map<String, String> params = new HashMap<>();
		StringJoiner joiner = new StringJoiner(",");
		for(String usedLibrary : usedLibraries) {
			joiner.add(usedLibrary);
		}
		params.put("used-libraries", joiner.toString());
		params.put("service-name", SERVICE_NAME_OK_VALUE);
		params.put("language-code", LANGUAGE_CODE_OK_VALUE);
		params.put("code", CODE_OK_VALUE);
		
		ErrorMessageResponseResource response = callGetEndpoint(BASE_V1_ENDPOINT + "/find-one", HttpStatus.OK.value(), ErrorMessageResponseResource.class, params);
		
		assertEquals(errorMessageEntity.getId(), response.getId());
	}
	
	@Test
	public void test_findOneErrorMessage_ko() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		Map<String, String> params = new HashMap<>();
		StringJoiner joiner = new StringJoiner(",");
		for(String usedLibrary : usedLibraries) {
			joiner.add(usedLibrary);
		}
		params.put("used-libraries", joiner.toString());
		params.put("service-name", "a");
		params.put("language-code", "a");
		params.put("code", "a");
		
		ErrorResponseResource response = callGetEndpoint(BASE_V1_ENDPOINT + "/find-one", HttpStatus.NOT_FOUND.value(), ErrorResponseResource.class, params);
		
		assertEquals("errorMessageWithRequestParametersNotFound", response.getMessage());
	}
	
	@Test
	public void test_findAllErrorMessage_ok() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		errorMessageSpringDataRepository.save(ErrorMessageEntity.builder().serviceName(SERVICE_NAME_OK_VALUE).languageCode(LANGUAGE_CODE_OK_VALUE).code(CODE_OK_VALUE).message(MESSAGE_OK_VALUE).build());
		
		ErrorMessagePaginationResponseResource response = callGetEndpoint(BASE_V1_ENDPOINT, HttpStatus.OK.value(), ErrorMessagePaginationResponseResource.class);
		System.out.println();
		assertEquals(false, response.getErrorMessages().isEmpty());
	}
	
	@Test
	public void test02_findAllErrorMessage_ok() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		errorMessageSpringDataRepository.save(ErrorMessageEntity.builder().serviceName(SERVICE_NAME_OK_VALUE).languageCode(LANGUAGE_CODE_OK_VALUE).code(CODE_OK_VALUE).message(MESSAGE_OK_VALUE).build());
		Map<String, String> params = new HashMap<>();
		params.put("page", null);
		params.put("size", null);
		params.put("sort", null);
		params.put("service-name", SERVICE_NAME_OK_VALUE);
		params.put("language-code", LANGUAGE_CODE_OK_VALUE);
		params.put("code", CODE_OK_VALUE);
		
		ErrorMessagePaginationResponseResource response = callGetEndpoint(BASE_V1_ENDPOINT, HttpStatus.OK.value(), ErrorMessagePaginationResponseResource.class);
		System.out.println();
		assertEquals(false, response.getErrorMessages().isEmpty());
	}
}
