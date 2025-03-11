package com.example.gconsultaion.repository;

import com.example.gconsultaion.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
