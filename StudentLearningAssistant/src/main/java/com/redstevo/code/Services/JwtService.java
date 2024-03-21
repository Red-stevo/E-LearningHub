package com.redstevo.code.Services;

import com.redstevo.code.Tables.AuthTable;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@Component
@RequiredArgsConstructor
public class JwtService {


    @Value("${key}")
    private String secreteKey;
    public String generateToken(AuthTable authTable){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", authTable.getUserId());
        claims.put("role", authTable.getRole());

        return Jwts
                .builder()
                .subject(authTable.getUsername())
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .signWith(getKey())
                .compact();
    }

    public Claims parseToken(String jwt){
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    private <T> T getClaims(String jwt, Function<Claims, T> claimsExtractor){
        Claims claims = parseToken(jwt);
        return claimsExtractor.apply(claims);
    }


    private SecretKey getKey() {
        byte[] bytes = Decoders.BASE64URL.decode(secreteKey);
        return Keys.hmacShaKeyFor(bytes);
    }
}
