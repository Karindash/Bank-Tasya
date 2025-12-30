package com.example.trial.banktasya.util;

import com.example.trial.banktasya.dto.response.ApiResponse;
import com.example.trial.banktasya.dto.response.ErrorResponse;
import com.example.trial.banktasya.dto.response.PagingResponse;
import com.example.trial.banktasya.dto.response.StatusResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {
    // build single response
    public static <T> ResponseEntity<ApiResponse<T>> buildSingleResponse(
            HttpStatus httpStatus,
            String message,
            T data) {

        StatusResponse status = StatusResponse.builder()
                .code(httpStatus.value())
                .description(message)
                .build();
        // Data
        ApiResponse<T> response = ApiResponse.<T>builder()
                .status(status)
                .data(data)
                .build();

        // return
        return ResponseEntity.status(httpStatus).body(response);
    }

    // build page response
    public static <T> ResponseEntity<ApiResponse<List<T>>> buildPagedResponse(
            HttpStatus httpStatus,
            String message,
            Page<T> page) {
        // StatusResponse
        StatusResponse status = StatusResponse.builder()
                .code(httpStatus.value())
                .message(httpStatus.name())
                .description(message)
                .build();
        // Paging
        PagingResponse paging = PagingResponse.builder()
                .page(page.getNumber() + 1)
                .rowsPerPage(page.getSize())
                .totalRows(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .build();

        // Data
        ApiResponse<List<T>> response = ApiResponse.<List<T>>builder()
                .status(status)
                .data(page.getContent())
                .build();

        // return
        return ResponseEntity.status(httpStatus).body(response);
    }

    // build error response
    public static ResponseEntity<ErrorResponse> buildErrorResponse(
            HttpStatus httpStatus,
            String message,
            List<String> errors) {

        StatusResponse status = StatusResponse.builder()
                .code(httpStatus.value())
                .description(message)
                .build();
        // Data
        ErrorResponse response = ErrorResponse.builder()
                .status(status)
                .errors(errors)
                .build();

        // return
        return ResponseEntity.status(httpStatus).body(response);
    }
}
