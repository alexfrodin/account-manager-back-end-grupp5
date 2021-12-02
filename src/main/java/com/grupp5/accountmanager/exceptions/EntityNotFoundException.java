package com.grupp5.accountmanager.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id){
     super(String.format("Entity with id %d not found",id));
    }


}
