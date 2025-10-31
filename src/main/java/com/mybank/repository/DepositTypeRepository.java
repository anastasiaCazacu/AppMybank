package com.mybank.repository;

import com.mybank.entity.DepositType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepositTypeRepository extends JpaRepository<DepositType, Long> {
    Optional<DepositType> findByCode(String code);
}
