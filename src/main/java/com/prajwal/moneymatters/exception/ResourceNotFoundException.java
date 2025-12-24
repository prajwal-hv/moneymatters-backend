package com.prajwal.moneymatters.exception;

public class ResourceNotFoundException  extends RuntimeException{
    public ResourceNotFoundException (String message){
        super(message);
    }
}
