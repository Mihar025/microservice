package com.misha.order.handler;

import com.misha.order.exceptions.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handle(BusinessException e){
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e){
        var errors =  new HashMap<String, String>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError)error).getField();
                    var errorMsg = error.getDefaultMessage();
                    errors.put(fieldName, errorMsg);
                });



        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(errors));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handle(EntityNotFoundException e){
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(e.getMessage());
    }





}
