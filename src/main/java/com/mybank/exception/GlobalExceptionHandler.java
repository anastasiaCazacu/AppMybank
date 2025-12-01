package com.mybank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //  Tratare pentru excepții de validare (ex: @Valid eșuat)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(buildBody("Validation failed", errors), HttpStatus.BAD_REQUEST);
    }

    //  Tratare pentru IllegalArgumentException (ex: validări în servicii)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(buildBody(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    //  Tratare generică pentru orice altă excepție
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex) {
        return new ResponseEntity<>(buildBody("Eroare internă: " + ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //exceptie personalizata
    @ExceptionHandler(CreditValidationException.class)
    public ResponseEntity<Object> handleCreditValidation(CreditValidationException ex) {
        return new ResponseEntity<>(buildBody(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    // Helper pentru structură de răspuns
    private Map<String, Object> buildBody(String message, Object details) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", message);
        if (details != null) body.put("details", details);
        return body;
    }
}