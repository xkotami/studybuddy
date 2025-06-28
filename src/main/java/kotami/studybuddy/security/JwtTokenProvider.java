package kotami.studybuddy.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Secret key (Base64-encoded); configure in application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    // Token validity in milliseconds; configure in application.properties
    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generate a JWT token using username from Authentication.
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extract username (subject) from JWT.
     */
    public String getUsernameFromJwt(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build().parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * Validate the token (signature + expiration).
     */
    public boolean validateToken(String authToken) {
        try {

            Jwts.parser()
                    .verifyWith((SecretKey) getSigningKey())
                    .build()
                    .parse(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException |
                 MalformedJwtException e) {
            // invalid signature or malformed
        } catch (ExpiredJwtException e) {
            // token expired
        } catch (UnsupportedJwtException e) {
            // unsupported token
        } catch (IllegalArgumentException e) {
            // empty claims string
        }
        return false;
    }
}