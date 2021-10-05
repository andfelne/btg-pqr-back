package com.bgt.pqr.exceptions;

public class PQRException extends RuntimeException {

private static final long serialVersionUID = 1L;

    public PQRException(final String message) {
        super(message, null);
    }

}
