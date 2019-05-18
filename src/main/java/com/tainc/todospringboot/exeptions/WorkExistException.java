package com.tainc.todospringboot.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class WorkExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public WorkExistException(String message) {
        super(message);
    }
}
