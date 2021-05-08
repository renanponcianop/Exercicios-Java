package com.exercicos.ex5.services.exceptions;

public class MarcaNotFoundException extends RuntimeException {

    public MarcaNotFoundException(String msg) {
        super (msg);
    }

    public MarcaNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
