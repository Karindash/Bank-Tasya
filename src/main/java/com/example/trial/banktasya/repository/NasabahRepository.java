package com.example.trial.banktasya.repository;

import com.example.trial.banktasya.entity.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NasabahRepository extends JpaRepository<Nasabah, String> {
    boolean existsByNik(String nik);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByFullName(String namaLengkap);

    Optional<Nasabah> findByNik(String nik);
    Optional<Nasabah> findByPhoneNumber(String phoneNumber);
    Optional<Nasabah> findByFullName(String namaLengkap);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, String id);
}
