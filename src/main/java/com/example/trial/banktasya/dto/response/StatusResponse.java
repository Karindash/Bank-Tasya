package com.example.trial.banktasya.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusResponse {
    private Integer code;
    private boolean isSuccess;
    private String message;
    private String description;
}
