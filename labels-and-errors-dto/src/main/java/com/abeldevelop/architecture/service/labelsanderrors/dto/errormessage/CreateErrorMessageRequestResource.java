package com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Create Error Message resource")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CreateErrorMessageRequestResource {

    @ApiModelProperty(notes="Name of the service", example="blog-category-service", required = true, position = 0)
    @NotNull
    @Size(min = ErrorMessageFieldsSizeConstants.SERVICE_NAME_MIN_SIZE, max = ErrorMessageFieldsSizeConstants.SERVICE_NAME_MAX_SIZE)
    private String serviceName;
    
    @ApiModelProperty(notes="Language code for the error message", example="es-ES", required = true, position = 1)
    @NotNull
    @Size(min = ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MIN_SIZE, max = ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MAX_SIZE)
    private String languageCode;
    
    @ApiModelProperty(notes="Code of the error message", example="categoryNameNotNull", required = true, position = 2)
    @NotNull
    @Size(min = ErrorMessageFieldsSizeConstants.CODE_MIN_SIZE, max = ErrorMessageFieldsSizeConstants.LANGUAGE_CODE_MAX_SIZE)
    private String code;
    
    @ApiModelProperty(notes="Message to display display", example="The category can not be null", required = true, position = 3)
    @NotNull
    @Size(min = ErrorMessageFieldsSizeConstants.MESSAGE_MIN_SIZE, max = ErrorMessageFieldsSizeConstants.MESSAGE_MAX_SIZE)
    private String message;
    
}