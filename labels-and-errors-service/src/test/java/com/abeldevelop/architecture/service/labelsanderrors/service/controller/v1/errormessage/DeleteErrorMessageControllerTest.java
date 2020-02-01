package com.abeldevelop.architecture.service.labelsanderrors.service.controller.v1.errormessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.abeldevelop.architecture.library.common.dto.exception.ErrorResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.repository.springdata.ErrorMessageSpringDataRepository;
import com.abeldevelop.architecture.service.labelsanderrors.service.controller.CommonTestController;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class DeleteErrorMessageControllerTest extends CommonTestController {

	private static final String BASE_V1_ENDPOINT = "/v1/error-message";
	
	private static final String SERVICE_NAME_OK_VALUE = "serviceName";
	private static final String LANGUAGE_CODE_OK_VALUE = "es-ES";
	private static final String CODE_OK_VALUE = "categoryNameNotNull";
	private static final String MESSAGE_OK_VALUE = "The category name can not be null";
	
	@Autowired
	private ErrorMessageSpringDataRepository errorMessageSpringDataRepository;
	
	
	@Test
	public void test_deleteErrorMessage_ko_notFound() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		Long errorMessageId = 99L;
		ErrorResponseResource response = callDeleteEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, HttpStatus.NOT_FOUND.value(), ErrorResponseResource.class);
		
		assertEquals("errorMessageIdNotExist", response.getMessage());
	}
	
	@Test
	public void test_deleteErrorMessage_ok() throws Exception {
		errorMessageSpringDataRepository.deleteAll();
		ErrorMessageEntity errorMessageEntitySaved = errorMessageSpringDataRepository.save(ErrorMessageEntity.builder().serviceName(SERVICE_NAME_OK_VALUE).languageCode(LANGUAGE_CODE_OK_VALUE).code(CODE_OK_VALUE).message(MESSAGE_OK_VALUE).build());
		Long errorMessageId = errorMessageEntitySaved.getId();
		callDeleteEndpoint(BASE_V1_ENDPOINT + "/" + errorMessageId, HttpStatus.NO_CONTENT.value(), Void.class);
		
		Optional<ErrorMessageEntity> errorMessageEntityOptional = errorMessageSpringDataRepository.findById(errorMessageId);
		
		assertEquals(false, errorMessageEntityOptional.isPresent());
	}
}
