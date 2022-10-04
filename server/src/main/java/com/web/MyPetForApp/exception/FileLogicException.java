package com.web.MyPetForApp.exception;

import lombok.Getter;

public class FileLogicException extends RuntimeException {
    @Getter
    private ExceptionCode exceptionCode;

    public FileLogicException(ExceptionCode exceptionCode){
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
