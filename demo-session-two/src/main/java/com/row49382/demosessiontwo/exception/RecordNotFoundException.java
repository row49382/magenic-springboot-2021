package com.row49382.demosessiontwo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(String msg) {
        super(msg);
    }

    public RecordNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
