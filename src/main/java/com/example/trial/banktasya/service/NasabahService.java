package com.example.trial.banktasya.service;

import com.example.trial.banktasya.dto.NasabahCreateRequest;
import com.example.trial.banktasya.dto.NasabahResponse;
import com.example.trial.banktasya.dto.NasabahUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NasabahService {
    NasabahResponse create(NasabahCreateRequest request);
    NasabahResponse update(String nik, NasabahUpdateRequest request);
    NasabahResponse getByNik(String nik);
    NasabahResponse getByFullName(String fullName);
    NasabahResponse getByPhoneNumber(String phoneNumber);
    Page<NasabahResponse> getAll(Pageable pageable);
    List<NasabahResponse> getAll();
    void delete(String id);
}
