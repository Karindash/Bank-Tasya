package com.example.trial.banktasya.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NasabahResponse {
    private String id;
    private String nik;
    private String phoneNumber;
    private String namaLengkap;
    private String alamat;
    private String tempatLahir;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
