package com.gklyphon.Customer.exception;

import com.gklyphon.Customer.exception.custom.ElementNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleElementNotFound(ElementNotFoundException ex) {
      log.error("Element not found: {}", ex.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
