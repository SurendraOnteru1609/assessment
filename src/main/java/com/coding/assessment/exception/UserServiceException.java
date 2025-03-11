package com.coding.assessment.exception;

import java.io.Serial;

public class UserServiceException extends Exception {

    @Serial
    private static final long serialVersionUID=1L;
    public UserServiceException(String message){
        super(message);
    }
}
