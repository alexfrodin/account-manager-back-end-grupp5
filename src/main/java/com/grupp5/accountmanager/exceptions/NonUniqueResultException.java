package com.grupp5.accountmanager.exceptions;

public class NonUniqueResultException extends RuntimeException{

    public NonUniqueResultException(Long id){
        super(String.format("User already exists with %d id:",id));
    }
}
