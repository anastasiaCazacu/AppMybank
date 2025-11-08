package com.mybank.service;

import com.mybank.config.JwtProperties;
import com.mybank.entity.RefreshToken;
import com.mybank.entity.User;
import com.mybank.repository.RefreshTokenRepository;
import com.mybank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService
{
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh.expiration}")
    private Long refreshExpiration;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProperties jwtProperties;

    String secret = jwtProperties.getSecret();
    Long refreshExp = jwtProperties.getRefreshExpiration();

    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit"));
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusMillis(refreshExpiration));
        return refreshTokenRepository.save(token);
    }

    public boolean validateToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalid"));
        return !refreshToken.getExpiryDate().isBefore(Instant.now());
    }

    public void deleteToken(String token) {
        refreshTokenRepository.findByToken(token)
                .ifPresent(refreshTokenRepository::delete);
    }

    public void deleteAllByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit")); //dacă utilizatorul nu există, se aruncă o excepție clară
        refreshTokenRepository.deleteByUser(user);
    }
}


