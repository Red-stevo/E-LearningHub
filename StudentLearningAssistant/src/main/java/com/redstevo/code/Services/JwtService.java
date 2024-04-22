package com.redstevo.code.Services;

import com.redstevo.code.CustomExceptions.InvalidAccessTokenException;
import com.redstevo.code.CustomExceptions.InvalidRequestException;
import com.redstevo.code.Repositories.TokensRepository;
import com.redstevo.code.Tables.AuthTable;
import com.redstevo.code.Tables.TokensTable;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.file.LinkOption;
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

    private final TokensRepository tokensRepository;

    public String generateToken(AuthTable authTable){
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", authTable.getUserId());
        claims.put("role", authTable.getRole());

        return Jwts
                .builder()
                .subject(authTable.getUsername())
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*5))
                .signWith(getKey())
                .compact();
    }

    public Boolean isValid(UserDetails authTable, String jwt){
        log.info("Checking token validity");
        return !(isExpired(jwt)) && !(isTokenLoggedOut(jwt)) && (isValidUser(jwt, authTable));
    }

    public String getUsername(String jwt){
        return getClaims(jwt, Claims::getSubject);
    }

    private Boolean isValidUser(String jwt, UserDetails authTable){
        log.info("Checking the username validity");
        return getUsername(jwt).equals(authTable.getUsername());
    }
    private Boolean isTokenLoggedOut(String jwt){

        log.info("checking where the token is logged out");

        Boolean isLoggedOut = tokensRepository.findByToken(jwt).orElseThrow(
                () -> new InvalidRequestException("Invalid Token Passed")
        ).getIsLoggedOut();

        if(isLoggedOut)
            throw new InvalidAccessTokenException("Token is Logged out");

        return false;
    }

    private Boolean isExpired(String jwt){
        Boolean isExpired = getClaims(jwt, Claims::getExpiration).before(new Date(System.currentTimeMillis()));

        /*If the token is expired, we need to mark it as logged out*/
        if(isExpired){
            TokensTable tokensTable = tokensRepository.findByToken(jwt).orElseThrow(
                    () -> {
                        log.error("Token Is Expired");
                        return new InvalidRequestException("Invalid Token passed");
                    }
            );
            tokensTable.setIsLoggedOut(true);
            log.warn("token passed is expired");

            log.info("logging out the token");
            /*Updating the database*/
            tokensRepository.save(tokensTable);
        }

        return isExpired;
    }

    private Claims parseToken(String jwt){
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
