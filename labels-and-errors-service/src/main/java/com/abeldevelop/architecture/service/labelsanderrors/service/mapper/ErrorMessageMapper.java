package com.abeldevelop.architecture.service.labelsanderrors.service.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.CreateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.UpdateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.model.ErrorMessageEntity;
import com.abeldevelop.architecture.service.labelsanderrors.service.domain.ErrorMessage;

@Component
public class ErrorMessageMapper {

	public ErrorMessage mapResourceToDomain(CreateErrorMessageRequestResource createErrorMessageRequestResource) {
        return ErrorMessage.builder()
                .serviceName(createErrorMessageRequestResource.getServiceName())
                .languageCode(createErrorMessageRequestResource.getLanguageCode())
                .code(createErrorMessageRequestResource.getCode())
                .message(createErrorMessageRequestResource.getMessage())
                .build();
    }
    
    public ErrorMessage mapResourceToDomain(Long id, UpdateErrorMessageRequestResource updateErrorMessageRequestResource) {
        return ErrorMessage.builder()
                .id(id)
                .serviceName(updateErrorMessageRequestResource.getServiceName())
                .languageCode(updateErrorMessageRequestResource.getLanguageCode())
                .code(updateErrorMessageRequestResource.getCode())
                .message(updateErrorMessageRequestResource.getMessage())
                .version(updateErrorMessageRequestResource.getVersion())
                .build();
    }
    
    public ErrorMessageResponseResource mapDomainToResource(ErrorMessage errorMessage) {
        return ErrorMessageResponseResource.builder()
                .id(errorMessage.getId())
                .serviceName(errorMessage.getServiceName())
                .languageCode(errorMessage.getLanguageCode())
                .code(errorMessage.getCode())
                .message(errorMessage.getMessage())
                .version(errorMessage.getVersion())
                .build();
    }
    
    public ErrorMessageEntity mapDomainToEntity(ErrorMessage errorMessage) {
        return ErrorMessageEntity.builder()
                .serviceName(errorMessage.getServiceName())
                .languageCode(errorMessage.getLanguageCode())
                .code(errorMessage.getCode())
                .message(errorMessage.getMessage())
                .build();
    }
    
    public ErrorMessageEntity mapDomainToEntity(ErrorMessage errorMessage, ErrorMessageEntity errorMessageEntity) {
        errorMessageEntity.setServiceName(errorMessage.getServiceName());
        errorMessageEntity.setLanguageCode(errorMessage.getLanguageCode());
        errorMessageEntity.setCode(errorMessage.getCode());
        errorMessageEntity.setMessage(errorMessage.getMessage());
        return errorMessageEntity;
    }
    
    public ErrorMessage mapEntityToDomain(ErrorMessageEntity errorMessageEntity) {
        return ErrorMessage.builder()
                .id(errorMessageEntity.getId())
                .serviceName(errorMessageEntity.getServiceName())
                .languageCode(errorMessageEntity.getLanguageCode())
                .code(errorMessageEntity.getCode())
                .message(errorMessageEntity.getMessage())
                .version(errorMessageEntity.getVersion())
                .build();
    }
    
}
