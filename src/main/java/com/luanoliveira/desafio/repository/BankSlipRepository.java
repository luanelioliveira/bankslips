package com.luanoliveira.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanoliveira.desafio.model.BankSlip;

@Repository
public interface BankSlipRepository extends JpaRepository<BankSlip, String> {
}
