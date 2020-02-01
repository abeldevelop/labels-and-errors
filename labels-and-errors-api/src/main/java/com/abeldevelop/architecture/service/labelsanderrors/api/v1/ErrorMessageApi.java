package com.abeldevelop.architecture.service.labelsanderrors.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.abeldevelop.architecture.library.common.dto.exception.ErrorResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.CreateErrorMessageRequestResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessagePaginationResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageResponseResource;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.ErrorMessageSort;
import com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage.UpdateErrorMessageRequestResource;
import com.abeldevelop.architecture.starter.api.SpringFoxConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags= {"ErrorMessages"})
@RequestMapping("/v1/error-message")
public interface ErrorMessageApi {

    @ApiOperation(value = "Create new error message")
    @ApiResponses({ 
        @ApiResponse(code = 201, response = ErrorMessageResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_201_MESSAGE), 
        @ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
        @ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorMessageResponseResource executeCreate(@ApiParam(name="errorMessage", value="Error Message to create", required = true) @RequestBody CreateErrorMessageRequestResource createErrorMessageRequestResource);

    @ApiOperation(value = "Update error message")
    @ApiResponses({ 
        @ApiResponse(code = 200, response = ErrorMessageResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_200_MESSAGE), 
        @ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
        @ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
        @ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE)
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessageResponseResource executeUpdate(
            @ApiParam(name="id", value="ID of the error message", required = true, example="1") @PathVariable("id") Long id,
            @ApiParam(name="category", value="Error Message to updated", required = true) @RequestBody UpdateErrorMessageRequestResource updateErrorMessageRequestResource);
    
    @ApiOperation(value = "Delete error message")
    @ApiResponses({ 
        @ApiResponse(code = 204, response = Void.class, message = SpringFoxConstants.API_RESPONSE_CODE_204_MESSAGE), 
        @ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
        @ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
        @ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE)
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void executeDelete(@ApiParam(name="id", value="ID of the error message", required = true, example="1") @PathVariable("id") Long id);
    
    @ApiOperation(value = "Find error message by ID")
    @ApiResponses({ 
        @ApiResponse(code = 200, response = ErrorMessageResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_204_MESSAGE), 
        @ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
        @ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
        @ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE)
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessageResponseResource executeFindById(@ApiParam(name="id", value="ID of the error message", required = true, example="1") @PathVariable("id") Long id);
    
    @ApiOperation(value = "Find error messages by params")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "service-name", value = "Service name to search", required = true, example="fir", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "language-code", value = "Language code to search", required = true, example="fir", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "code", value = "Code to search", required = true, example="fir", dataType = "string", paramType = "query")
    })
    @ApiResponses({ 
        @ApiResponse(code = 200, response = ErrorMessageResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_200_MESSAGE),
        @ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
        @ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
        @ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE)
    })
    @GetMapping("/find-one")
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessageResponseResource executeFindOne(
            @RequestParam(name = "service-name", required = true) String serviceName, 
            @RequestParam(name = "language-code", required = true) String languageCode, 
            @RequestParam(name = "code", required = true) String code);
    
    @ApiOperation(value = "Find all error messages")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "Number of page", required = false, example="1", defaultValue="1", dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "Size of page", required = false, example="1", defaultValue="10", dataType = "int", paramType = "query"),
        @ApiImplicitParam(name = "sort", value = "Field and type to sort the fields", required = false, defaultValue = "CODE_DESC", example="CODE_DESC", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "service-name", value = "Part of service name to search", required = false, example="fir", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "language-code", value = "Part of language code to search", required = false, example="fir", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "code", value = "Part of code to search", required = false, example="fir", dataType = "string", paramType = "query")
    })
    @ApiResponses({ 
        @ApiResponse(code = 200, response = ErrorMessagePaginationResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_200_MESSAGE),
        @ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_400_MESSAGE),
        @ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_404_MESSAGE),
        @ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConstants.API_RESPONSE_CODE_500_MESSAGE)
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ErrorMessagePaginationResponseResource executeFindAll(
            @RequestParam(name = "page", required = false) Integer page, 
            @RequestParam(name = "size", required = false) Integer size, 
            @RequestParam(name = "sort", required = false) ErrorMessageSort sort,
            @RequestParam(name = "service-name", required = false) String serviceName, 
            @RequestParam(name = "language-code", required = false) String languageCode, 
            @RequestParam(name = "code", required = false) String code);
    
}