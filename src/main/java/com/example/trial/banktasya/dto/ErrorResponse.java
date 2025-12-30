package com.example.trial.banktasya.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private StatusResponse status;
    private List<String> errors;
}
