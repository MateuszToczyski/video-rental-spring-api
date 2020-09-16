package com.videos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessRuleViolationException.class)
    @ResponseBody
    public ErrorInfo handleBadRequest(Exception ex, HttpServletRequest request) {
        return new ErrorInfo(request.getRequestURL().toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class, ExternalDatasourceException.class})
    @ResponseBody
    public ErrorInfo handleNotFound(Exception ex, HttpServletRequest request) {
        return new ErrorInfo(request.getRequestURL().toString(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleUncaughtException(Exception ex, HttpServletRequest request) {
        return new ErrorInfo(request.getRequestURL().toString(), ex.getMessage());
    }
}
