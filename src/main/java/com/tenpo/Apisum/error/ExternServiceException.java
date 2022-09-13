package com.tenpo.Apisum.error;

public class ExternServiceException extends RuntimeException {
    private String msg;
    public ExternServiceException(String error_calling_extern_service) {
        msg = error_calling_extern_service;
    }
}
