package com.example.trial.banktasya.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "nasabah")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Nasabah {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true, length = 16)
    private Long nik;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String namaLengkap;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String alamat;

    @Column(nullable = false)
    private String tempatLahir;

    @Column(nullable = false)
    private LocalDate tanggalLahir;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
