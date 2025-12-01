package com.mybank.repository;

import com.mybank.entity.Role;
import com.mybank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Caută utilizatorul după username (pentru autentificare)
    Optional<User> findByUsername(String username);

    // Caută utilizatorul după email (pentru validare sau resetare parolă)
    Optional<User> findByEmail(String email);

    // Verifică dacă username-ul există deja
    boolean existsByUsername(String username);

    // Verifică dacă emailul există deja
    boolean existsByEmail(String email);

    // Găsește toți utilizatorii cu un anumit rol (entitate Role)
    List<User> findByRole(Role role);

    // Găsește toți utilizatorii cu un anumit nume de rol (ex: "CLIENT")
    List<User> findByRole_Name(String name);

    // Găsește utilizatori născuți înainte de o anumită dată (ex: majori)
    List<User> findByDateOfBirthBefore(Date date);

    // Găsește utilizatori cu un anumit rol și născuți înainte de o dată
    List<User> findByRole_NameAndDateOfBirthBefore(String roleName, Date date);
}