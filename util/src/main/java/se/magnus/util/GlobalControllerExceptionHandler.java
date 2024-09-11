package se.magnus.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import se.magnus.api.exceptions.InvalidInputException;
import se.magnus.api.exceptions.NotFoundException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    private @ResponseBody HttpErrorInfo handleNoFoundException(
        ServerHttpRequest request, NotFoundException ex) {
            return createHttpErrorInfo(NOT_FOUND, request, ex);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(InvalidInputException.class)
    public @ResponseBody HttpErrorInfo handleInvalidInputException(
        ServerHttpRequest request, InvalidInputException ex) {
        return createHttpErrorInfo(UNPROCESSABLE_ENTITY, request, ex);
    }

    private HttpErrorInfo createHttpErrorInfo(
        HttpStatus status, ServerHttpRequest request, Exception ex
    ){
        final String path = request.getPath().pathWithinApplication().value();
        final String message = ex.getMessage();
        LOG.debug("Returning http status: {} for path: {}, message: {}", status, path, message);
        return new HttpErrorInfo(path, status, message);
    }
}
