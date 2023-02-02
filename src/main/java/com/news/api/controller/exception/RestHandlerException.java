package com.news.api.controller.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleNullPointer(HttpServletRequest request, IllegalArgumentException exception) {
        StandardError response = new StandardError(HttpStatus.UNPROCESSABLE_ENTITY, "exception.getMessage()");
        response.setMessage("The row for address is not existent: " + request.getRequestURI());
        return buildResponseEntity(response);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleAuthenticationException(HttpServletRequest request,
            NullPointerException exception) {
        StandardError response = new StandardError(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return buildResponseEntity(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        StandardError response = new StandardError(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return buildResponseEntity(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Object> handleInvalidTransaction(TransactionSystemException exception) {
        StandardError response = new StandardError(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
        return buildResponseEntity(response);
    }

    private ResponseEntity<Object> buildResponseEntity(StandardError errorResponse) {
        return new ResponseEntity<Object>(errorResponse, errorResponse.getStatusHttp());
    }
}
