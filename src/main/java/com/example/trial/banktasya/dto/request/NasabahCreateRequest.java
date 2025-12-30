package com.example.trial.banktasya.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NasabahCreateRequest {
    @NotBlank(message = "NIK tidak boleh kosong")
    @Pattern(regexp = "^[0-9]{16}$", message = "NIK harus 16 digit angka")
    private String nik;

    @NotBlank(message = "Nomor telepon tidak boleh kosong")
    @Pattern(regexp = "^(\\+62|62|0)[0-9]{9,12}$", message = "Format nomor telepon tidak valid")
    private String phoneNumber;

    @NotBlank(message = "Nama lengkap tidak boleh kosong")
    @Size(min = 3, message = "Nama lengkap harus minimal 3 karakter")
    private String fullName;

    @NotBlank(message = "Alamat tidak boleh kosong")
    @Size(min = 10, message = "Alamat harus antara 10-500 karakter") // kasih verifikasi detail alamat
    private String address;

    @NotBlank(message = "Tempat lahir tidak boleh kosong")
    @Size(min = 3, message = "Tempat lahir harus lebih dari 3 karakter")
    private String birthPlace;

    @NotNull(message = "Tanggal lahir tidak boleh kosong")
    @Past(message = "Tanggal lahir harus di masa lalu") // verifikasi 17 tahun+    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
