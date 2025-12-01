package com.mybank.repository;

import com.mybank.entity.Deposit;
import com.mybank.entity.DepositStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    // gasește toate depozitele pentru un utilizator
    List<Deposit> findByUserId(Long userId);

    //  gaseste depozitele active pentru un utilizator
    List<Deposit> findByUserIdAndStatus(Long userId, DepositStatus status);

    //  imi gaseste depozitele după status
    List<Deposit> findByStatus(DepositStatus status);

    //  imi gaseste depozitele care expiră înainte de o anumită dată
    List<Deposit> findByEndDateBefore(java.util.Date date);

}
