package com.ibar.demo.exceptions;

public class PhotoNotFound extends RuntimeException {
    public PhotoNotFound(String message) {
        super(message);
    }
}
