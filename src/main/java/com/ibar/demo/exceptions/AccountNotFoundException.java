package com.ibar.demo.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String str) {
        super(str);
    }

}
