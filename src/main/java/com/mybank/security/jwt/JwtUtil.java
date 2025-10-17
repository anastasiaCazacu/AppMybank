package com.mybank.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "secret_key_for_mybank"; //  cheia secreta
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 ore

    //metoda care genereaza cheia JWT
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // cine e utilizatorul
                .claim("role", userDetails.getAuthorities().iterator().next().getAuthority()) // rolul
                .setIssuedAt(new Date(System.currentTimeMillis())) // când a fost emis
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // când expiră
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // semnătura
                .compact();

    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }
}
