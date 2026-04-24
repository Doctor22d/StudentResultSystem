package com.example.uploadResult.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

// JwtUtils.java
@Component
public class JwtUtils {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    // ✅ Return SecretKey directly — no unsafe cast needed
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String userId, List<String> roles) {
        return Jwts.builder()
                .subject(userId)
                .claim("roles", roles) // ✅ Store as List for multi-role support
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1800000))
                .signWith(secretKey())
                .compact();
    }

    public String extractSubject(String jwtToken) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey())
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            log.error(">>> TOKEN VALIDATION FAILED: {}", e.getMessage());
            return null;
        }
    }

    public List<SimpleGrantedAuthority> extractRoles(String jwtToken) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey())
                    .build()
                    .parseSignedClaims(jwtToken)
                    .getPayload();

            Object rolesObj = claims.get("roles");

            // ✅ Only add "ROLE_" if not already present
            if (rolesObj instanceof String role) {
                String prefixed = role.startsWith("ROLE_") ? role : "ROLE_" + role;
                return List.of(new SimpleGrantedAuthority(prefixed));
            }

            if (rolesObj instanceof List<?> roleList) {
                return roleList.stream()
                        .map(r -> {
                            String role = r.toString();
                            return new SimpleGrantedAuthority(
                                    role.startsWith("ROLE_") ? role : "ROLE_" + role
                            );
                        })
                        .toList();
            }

            return List.of();

        } catch (Exception e) {
            return List.of();
        }
    }
}