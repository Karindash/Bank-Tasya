package com.example.trial.banktasya.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private StatusResponse status;
    private T data;

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .status(
                        StatusResponse.builder()
                                .code(200)
                                .isSuccess(true)
                                .description(message)
                                .build()
                )
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(String message) {
        return ApiResponse.<T>builder()
                .status(StatusResponse.builder()
                                .code(200)
                                .isSuccess(true)
                                .description(message)
                                .build()
                )
                .build();
    }
}
