package se.magnus.util;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class HttpErrorInfo {
    
    private final ZonedDateTime timestamp;
    private final String path;
    private final HttpStatus status;
    private final String message;

    
    public HttpErrorInfo() {
        timestamp = null;
        path = null;
        status = null;
        message = null;
    }


    public HttpErrorInfo(String path, HttpStatus status, String message) {
        timestamp = ZonedDateTime.now();
        this.path = path;
        this.status = status;
        this.message = message;
    }


    public ZonedDateTime getTimestamp() {
        return timestamp;
    }


    public String getPath() {
        return path;
    }


    public HttpStatus getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }
}
