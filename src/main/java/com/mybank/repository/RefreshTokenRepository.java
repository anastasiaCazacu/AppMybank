package com.mybank.repository;

import com.mybank.entity.RefreshToken;
import com.mybank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Acum : Poți căuta tokenul și șterge toate tokenurile unui utilizator la logout
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}

