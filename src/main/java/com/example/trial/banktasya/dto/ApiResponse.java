package com.example.trial.banktasya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean isSuccess;
    private StatusResponse status;
    private T data;
    private PagingResponse paging;

    public static ApiResponse<NasabahResponse> success(String msg, NasabahResponse response) {
        return ApiResponse.<NasabahResponse>builder().build();
    }
}

