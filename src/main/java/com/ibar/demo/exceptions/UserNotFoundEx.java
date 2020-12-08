package com.ibar.demo.exceptions;

public class UserNotFoundEx extends RuntimeException {

    public UserNotFoundEx(String str) {
        super(str);
    }

}
