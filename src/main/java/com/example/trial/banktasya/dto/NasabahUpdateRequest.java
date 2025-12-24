package com.example.trial.banktasya.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class NasabahUpdateRequest {
    @Pattern(regexp = "^(\\+62|62|0)[0-9]{9,12}$", message = "Format nomor telepon tidak valid")
    private String phoneNumber;

    @Size(min = 3, message = "Nama lengkap harus lebih dari 3 karakter")
    private String namaLengkap;

    @Size(min = 10, message = "Alamat harus lebih dari 10 karakter")
    private String alamat;

    @Size(min = 3, message = "Tempat lahir lebih dari 3 karakter")
    private String tempatLahir;

    @Past(message = "Tanggal lahir harus di masa lalu")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;
}
