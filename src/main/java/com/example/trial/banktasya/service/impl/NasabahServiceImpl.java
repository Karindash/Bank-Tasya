package com.example.trial.banktasya.service.impl;

import com.example.trial.banktasya.dto.NasabahCreateRequest;
import com.example.trial.banktasya.dto.NasabahResponse;
import com.example.trial.banktasya.dto.NasabahUpdateRequest;
import com.example.trial.banktasya.entity.Nasabah;
import com.example.trial.banktasya.exception.DuplicateResourceException;
import com.example.trial.banktasya.exception.ResourceNotFoundException;
import com.example.trial.banktasya.repository.NasabahRepository;
import com.example.trial.banktasya.service.NasabahService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NasabahServiceImpl implements NasabahService {
    private final NasabahRepository nasabahRepository;

    @Override
    public NasabahResponse create(NasabahCreateRequest request) {
        log.info("Creating new nasabah with NIK: {}", request.getNik());
        var nikValidator = nasabahRepository.existsByNik(request.getNik());
        var phoneNumberValidator = nasabahRepository.existsByPhoneNumber(request.getPhoneNumber());

        if(nikValidator){
            throw new DuplicateResourceException("NIK sudah terdaftar: "+ request.getNik());
        }

        if(phoneNumberValidator){
            throw new DuplicateResourceException("Nomor handphone sudah terdaftar: "+ request.getPhoneNumber());
        }

        Nasabah nasabah = Nasabah.builder()
                .nik(Long.valueOf(request.getNik()))
                .phoneNumber(request.getPhoneNumber())
                .fullName(request.getFullName())
                .address(request.getAddress())
                .birthPlace(request.getBirthPlace())
                .birthDate(request.getBirthDate())
                .build();
        Nasabah savedNasabah = nasabahRepository.save(nasabah);
        log.info("Nasabah created successfully with ID: {}", savedNasabah.getId());

        return mapToResponse(savedNasabah);
    }

    @Override
    public NasabahResponse update(String nik, NasabahUpdateRequest request) {
        log.info("Mengubah data nasabah berdasarkan NIK: {}", nik);

        Nasabah nasabah = nasabahRepository.findByNik(nik)
                .orElseThrow(() -> new ResourceNotFoundException("Nasabah tidak ditemukan dengan NIK: " + nik));

        // Hanya boleh mengupdate phoneNumber dan alamat

        // Update hanya field yang tidak null
        if (request.getPhoneNumber() != null) {
            if (nasabahRepository.existsByPhoneNumberAndNikNot(request.getPhoneNumber(), nik)) {
                throw new DuplicateResourceException("Nomor telepon sudah terdaftar: " + request.getPhoneNumber());
            }
            nasabah.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getAddress() != null) {
            nasabah.setAddress(request.getAddress());
        }

        Nasabah updatedNasabah = nasabahRepository.save(nasabah);
        log.info("Data nasabah berhasil diupdate: {}", nik);

        return mapToResponse(updatedNasabah);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NasabahResponse> getAll(Pageable pageable) {
        log.info("Getting all nasabah with pagination");

        Page<Nasabah> nasabahPage = nasabahRepository.findAll(pageable);
        return nasabahPage.map(this::mapToResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NasabahResponse> getAll() {
        log.info("Getting all nasabah");

        List<Nasabah> nasabahList = nasabahRepository.findAll();
        return nasabahList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        log.info("Deleting nasabah with ID: {}", id);

        if (!nasabahRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nasabah tidak ditemukan dengan ID: " + id);
        }

        nasabahRepository.deleteById(id);
        log.info("Nasabah deleted successfully with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public NasabahResponse getByNik(String nik) {
        log.info("Mendapatkan nasabah by NIK: {}", nik);

        Nasabah nasabah = nasabahRepository.findByNik(nik)
                .orElseThrow(() -> new ResourceNotFoundException("Nasabah tidak ditemukan dengan NIK: " + nik));

        return mapToResponse(nasabah);
    }

    @Override
    @Transactional(readOnly = true)
    public NasabahResponse getByFullName(String namaLengkap) {
        log.info("Mendapatkan nasabah by Nasabah dengan Nama: {}", namaLengkap);

        Nasabah nasabah = nasabahRepository.findByFullName(namaLengkap)
                .orElseThrow(() -> new ResourceNotFoundException("Nasabah dengan nama tersebut tidak ditemukan."));

        return mapToResponse(nasabah);
    }

    @Override
    @Transactional(readOnly = true)
    public NasabahResponse getByPhoneNumber(String phoneNumber) {
        log.info("Getting nasabah by phone number: {}", phoneNumber);

        Nasabah nasabah = nasabahRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Nasabah tidak ditemukan dengan nomor telepon: " + phoneNumber));

        return mapToResponse(nasabah);
    }

    private NasabahResponse mapToResponse(Nasabah nasabah) {
        return NasabahResponse.builder()
                .id(nasabah.getId())
                .nik(String.valueOf(nasabah.getNik()))
                .phoneNumber(nasabah.getPhoneNumber())
                .fullName(nasabah.getFullName())
                .address(nasabah.getAddress())
                .birthPlace(nasabah.getBirthPlace())
                .birthDate(nasabah.getBirthDate())
                .createdAt(nasabah.getCreatedAt())
                .updatedAt(nasabah.getUpdatedAt())
                .build();
    }
}
