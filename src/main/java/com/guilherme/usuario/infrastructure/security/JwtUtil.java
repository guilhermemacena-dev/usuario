package com.guilherme.usuario.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtUtil {

    private final String secretKey = "c3VhLWNoYXZlLXNlY3JldGEtc3VwZXItc2VndXJhLXF1ZS1kZXZlLXNlci1iZW0tbG9uZ2E=";

    private SecretKey getSecretKey() {
        byte[] key = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) 
                .issuedAt(new Date()) 
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) 
                .signWith(getSecretKey()) 
                .compact(); 
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey()) 
                .build()
                .parseSignedClaims(token) 
                .getPayload(); 
    }

    public String extrairEmailToken(String token) {
        
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
      
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
     
        final String extractedUsername = extrairEmailToken(token);

        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
