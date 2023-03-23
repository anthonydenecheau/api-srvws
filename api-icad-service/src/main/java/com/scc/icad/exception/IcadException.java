package com.scc.icad.exception;

public class IcadException extends RuntimeException {

    public IcadException(String message) {
        super(message);
    }

    public IcadException(String message, Throwable cause) {
        super(message, cause);
    }
}
