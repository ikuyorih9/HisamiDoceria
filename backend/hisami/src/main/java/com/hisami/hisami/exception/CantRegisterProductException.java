package com.hisami.hisami.exception;

public class CantRegisterProductException extends RuntimeException {
    public CantRegisterProductException(String message) {
        super(message);
    }
}
