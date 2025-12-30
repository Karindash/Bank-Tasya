package com.example.trial.banktasya.exception;

import com.example.trial.banktasya.dto.response.ErrorResponse;
import com.example.trial.banktasya.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {

        log.warn("Resource not found", ex);

        return ResponseUtil.buildErrorResponse(
                HttpStatus.NOT_FOUND,
                "Data tidak ditemukan",
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();

        return ResponseUtil.buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Request tidak valid",
                errors
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {

        log.error("Unhandled error", ex);

        return ResponseUtil.buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Kesalahan sistem",
                List.of("Unexpected error occurred")
        );
    }
}
