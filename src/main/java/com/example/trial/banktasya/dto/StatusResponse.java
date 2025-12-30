package com.example.trial.banktasya.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusResponse {
    private Integer code;
    private String description;
}
