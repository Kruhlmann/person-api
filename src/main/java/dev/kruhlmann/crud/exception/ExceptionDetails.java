package dev.kruhlmann.crud.exception;

import java.util.Date;

public class ExceptionDetails {
    protected Date date;
    protected String message;
    protected String details;

    public ExceptionDetails(Date timestamp, String message, String details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
