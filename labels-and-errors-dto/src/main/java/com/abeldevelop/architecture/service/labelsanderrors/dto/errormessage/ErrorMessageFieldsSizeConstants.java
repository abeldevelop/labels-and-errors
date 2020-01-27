package com.abeldevelop.architecture.service.labelsanderrors.dto.errormessage;

public class ErrorMessageFieldsSizeConstants {

    private ErrorMessageFieldsSizeConstants() {
        
    }
    
    public static final int SERVICE_NAME_MIN_SIZE = 5;
    public static final int SERVICE_NAME_MAX_SIZE = 50;
    
    public static final int LANGUAGE_CODE_MIN_SIZE = 2;
    public static final int LANGUAGE_CODE_MAX_SIZE = 5;
    
    public static final int CODE_MIN_SIZE = 5;
    public static final int CODE_MAX_SIZE = 50;
    
    public static final int MESSAGE_MIN_SIZE = 5;
    public static final int MESSAGE_MAX_SIZE = 255;
}