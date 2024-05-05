package com.microservices.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourcesNotFoundException extends RuntimeException{

    public ResourcesNotFoundException(String resourceName , String fieldName , String fieldValue){
        super(String.format("%s not found with given data %s : %s" , resourceName , fieldName , fieldValue));
    }
}
