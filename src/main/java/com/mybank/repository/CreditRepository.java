package com.mybank.repository;

import com.mybank.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    Credit save(Credit credit);

    // 🔍 Găsește credite într-un interval de date
    List<Credit> findByDateBetween(LocalDate start, LocalDate end);

    // 🔍 Găsește credite exact la o dată
    List<Credit> findByDate(LocalDate date);

    // 🔍 Găsește toate creditele asociate unui utilizator (prin entitatea intermediară)
    @Query("SELECT c FROM Credit c JOIN c.usersCredits uc WHERE uc.user.id = :userId")
    List<Credit> findAllByUserId(@Param("userId") Long userId);

    // 🔍 Găsește toate creditele aprobate de un utilizator
    @Query("SELECT c FROM Credit c JOIN c.usersCredits uc WHERE uc.approvedBy = :username")
    List<Credit> findAllApprovedBy(@Param("username") String username);
}
