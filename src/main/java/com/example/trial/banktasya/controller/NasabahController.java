package com.example.trial.banktasya.controller;

import com.example.trial.banktasya.dto.response.ApiResponse;
import com.example.trial.banktasya.dto.request.NasabahCreateRequest;
import com.example.trial.banktasya.dto.response.NasabahResponse;
import com.example.trial.banktasya.dto.request.NasabahUpdateRequest;
import com.example.trial.banktasya.service.NasabahService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nasabah")
@RequiredArgsConstructor
public class NasabahController {
    private final NasabahService nasabahService;

    @PostMapping
    public ResponseEntity<ApiResponse<NasabahResponse>> createNasabah(
            @Valid @RequestBody NasabahCreateRequest request) {
        NasabahResponse response = nasabahService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Nasabah berhasil didaftarkan", response));
    }

    @PutMapping("/{nik}")
    public ResponseEntity<ApiResponse<NasabahResponse>> updateNasabah(
            @PathVariable String nik,
            @Valid @RequestBody NasabahUpdateRequest request) {
        NasabahResponse response = nasabahService.update(nik, request);
        return ResponseEntity.ok(ApiResponse.success("Nasabah berhasil diperbarui", response));
    }

    @GetMapping("/{nik}")
    public ResponseEntity<ApiResponse<NasabahResponse>> getNasabahByNik(@PathVariable String nik) {
        NasabahResponse response = nasabahService.getByNik(nik);
        return ResponseEntity.ok(ApiResponse.success("Data nasabah ditemukan", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<NasabahResponse>>> getAllNasabah(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("ASC")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<NasabahResponse> response = nasabahService.getAll(pageable);

        return ResponseEntity.ok(ApiResponse.success("Data nasabah berhasil diambil", response));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<NasabahResponse>>> getAllNasabahList() {
        List<NasabahResponse> response = nasabahService.getAll();
        return ResponseEntity.ok(ApiResponse.success("Data nasabah berhasil diambil", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteNasabah(@PathVariable String id) {
        nasabahService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Nasabah berhasil dihapus", null));
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<ApiResponse<NasabahResponse>> getNasabahByPhoneNumber(
            @PathVariable String phoneNumber) {
        NasabahResponse response = nasabahService.getByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(ApiResponse.success("Data nasabah ditemukan", response));
    }

}
