package com.example.trial.banktasya.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private boolean isSuccess;
    private StatusResponse status;
    private List<String> errors;
}
