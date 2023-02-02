package com.fellaverse.backend.handler;

import com.fellaverse.backend.code.ReturnCode;
import com.fellaverse.backend.pojo.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * Default global exception handler
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        System.out.println("Global exception: " + e.getMessage());
        return ResultData.fail(ReturnCode.INTERNAL_SERVER_ERROR.getCode(),e.getMessage());
    }

}