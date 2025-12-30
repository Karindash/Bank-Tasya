package com.example.trial.banktasya.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NasabahUpdateRequest {
    @Pattern(regexp = "^(\\+62|62|0)[0-9]{9,12}$", message = "Format nomor telepon tidak valid")
    private String phoneNumber;

    @Size(min = 10, message = "Alamat harus lebih dari 10 karakter")
    private String address;

}
