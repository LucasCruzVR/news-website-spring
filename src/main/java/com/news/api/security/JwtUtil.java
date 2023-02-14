package com.news.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String jwtSecret = "EE2@09BDD3EE8E$4&2B3889612BC4E6F7#412$";
    private final Integer expiration = 600000;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    public Boolean validToken(String token) {
        Claims claims = getClaims(token);
        if (claims != null && !claims.isEmpty()) {
            String email = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (email != null && expirationDate != null && now.before(expirationDate)) {
                return true;
            }
        }
        return false;
    }

    public String getEmail(String token) {
        Claims claims = getClaims(token);
        if (!claims.isEmpty()) {
            return claims.getSubject();
        }
        return null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }
}
