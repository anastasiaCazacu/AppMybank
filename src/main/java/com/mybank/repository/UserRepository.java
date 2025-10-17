package com.mybank.repository;

import com.mybank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);// Caută utilizatorul după username

    List<User> findByRole(String role);  // pentru filtrare
}

