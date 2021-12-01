package com.grupp5.accountmanager.services;

import com.grupp5.accountmanager.exceptions.EntityNotFoundException;
import com.grupp5.accountmanager.exceptions.NonUniqueResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ExceptionService {

private final String SERVER_ERROR ="Internal Server Error";

@ExceptionHandler(EntityNotFoundException.class)

    public ResponseEntity notFoundException(EntityNotFoundException e){
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
}

@ExceptionHandler(NonUniqueResultException.class)
    public ResponseEntity nonUniqueException(NonUniqueResultException nex){
   return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(nex.getMessage());
}


}
