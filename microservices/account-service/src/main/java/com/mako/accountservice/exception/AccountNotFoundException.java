package com.mako.accountservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String errMessage) {
        super(errMessage);
    }

}
