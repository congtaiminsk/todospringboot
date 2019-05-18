package com.tainc.todospringboot.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WorkNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WorkNotFoundException(String exception) {
        super(exception);
    }

}
