package com.example.trial.banktasya.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false, unique = true)
    private Long NIK;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String namaLengkap;

    @Column(nullable = false)
    private String alamat;

    @Column(nullable = false)
    private String tempatLahir;

    @Column(nullable = false)
    private LocalDate tanggalLahir;
}
