package com.cognizant.membermicroservice.exception;

import com.cognizant.membermicroservice.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@ResponseStatus
public class PolicyExceptionHandler {
    @ExceptionHandler(PolicyNotFoundException.class)
    public ResponseEntity<ErrorMessage> providerNotFoundException(PolicyNotFoundException policyNotFoundException, WebRequest webRequest){
        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.BAD_REQUEST,policyNotFoundException.getMessage());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

    }
}
