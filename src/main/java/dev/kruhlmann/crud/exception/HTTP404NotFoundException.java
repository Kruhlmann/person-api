package dev.kruhlmann.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HTTP404NotFoundException extends Exception {
    public HTTP404NotFoundException(String message) {
        super(message);
    }
}
