package com.example.trial.banktasya.repository;

import com.example.trial.banktasya.entity.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NasabahRepository extends JpaRepository<Nasabah, Long> {
    boolean existedByNIK(Long NIK);
}
