package com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage;

public enum ErrorMessageSort {

    SERVICE_NAME_DESC("SERVICE_NAME_DESC"),
    CODE_DESC("CODE_DESC");
    
    private String value;
    
    private ErrorMessageSort(String value) {
        this.value = value;
    }
    
    public String getSort() {
        return value;
    }
    
}