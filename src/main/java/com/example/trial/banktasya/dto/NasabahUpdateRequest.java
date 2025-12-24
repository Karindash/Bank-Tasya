package com.example.trial.banktasya.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NasabahUpdateRequest {
    @Pattern(regexp = "^(\\+62|62|0)[0-9]{9,12}$", message = "Format nomor telepon tidak valid")
    private String phoneNumber;

    @Size(min = 3, message = "Nama lengkap harus lebih dari 3 karakter")
    private String fullName;

    @Size(min = 10, message = "Alamat harus lebih dari 10 karakter")
    private String address;

    @Size(min = 3, message = "Tempat lahir lebih dari 3 karakter")
    private String birthPlace;

    @Past(message = "Tanggal lahir harus di masa lalu")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
