package com.myblog3.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String str){
        super(str);
    }
}
