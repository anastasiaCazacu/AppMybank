package com.mybank.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    //private static final String SECRET_KEY ="bXlCYW5rQXBwU2VjcmV0S2V5MTIzIT8kKiYoQDMyU2VjdXJlU3RhdGljS2V5QmFuazY0"; //  cheia secreta, generam
    // prin comanda: openssl rand -base64 64
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 ore

    @Value("${jwt.secret}")
    private String secret;

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //metoda care genereaza cheia JWT
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // cine e utilizatorul
                .claim("role", userDetails.getAuthorities().iterator().next().getAuthority()) // rolul
                .setIssuedAt(new Date(System.currentTimeMillis())) // când a fost emis
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // când expiră
                .signWith(getSigningKey()) // semnătura
                .compact();

    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }
}
