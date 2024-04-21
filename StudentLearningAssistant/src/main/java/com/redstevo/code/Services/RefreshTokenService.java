package com.redstevo.code.Services;

import com.redstevo.code.CustomExceptions.InvalidRefreshToken;
import com.redstevo.code.Repositories.RefreshTokenRepository;
import com.redstevo.code.Tables.AuthTable;
import com.redstevo.code.Tables.RefreshTokenTable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final HttpServletRequest request;

    /*
    * This method generate a new uuid that is used as a refresh token
    * It ensures each user has only one refresh token available in the database i.e.
    * deletes other refresh token during generation.
    * The method also sets the expiry of a token{The expiration of the token spans to 14 days.}
    * */

    public String generateRefreshToken(AuthTable authTable){

        log.info("Generating refresh token");
        //generate the uuid
        String refreshToken = UUID.randomUUID().toString();

        //delete existing refresh tokens.
        refreshTokenRepository.deleteByAuthTable(authTable);

        //save the new refresh token
        RefreshTokenTable refreshTokenTable = new RefreshTokenTable();

        refreshTokenTable.setRefreshToken(refreshToken);
        refreshTokenTable.setAuthTable(authTable);
        refreshTokenTable.setExpirationDate(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 24 * 14));

        //save the refreshToken.
        refreshTokenRepository.save(refreshTokenTable);

        //return the refresh token string
        return refreshToken;
    }


   /*
   * This method checks whether the refresh token passed has expired
   * It checks the expiration time set during creation of the token
   * */

    private Boolean isExpired(String refreshToken){
        log.info("checking refresh token expiry");


        return refreshTokenRepository.
                findExpirationDateByRefreshToken(refreshToken)
                .orElseThrow(() -> new InvalidRefreshToken("RefreshToken passed was not recognized"))
                .compareTo(new Date()) < 0;
    }



    /*General purpose : check validity of a refreshToken
    This method extracts the refreshToken from the cookie in the HttpServletRequest
    and check if the tokenExist in the database.*/
    public AuthTable checkRefreshTokenValidity(){
        log.info("checking refreshToken validity");

        //extract the refresh token
        String refreshToken = request.getHeader("cookie");

        if(refreshToken == null || !refreshToken.startsWith("refresh_token="))
            throw  new InvalidRefreshToken("No refreshToken passed or Illegal RefreshToken modification");

        //extract the refreshToken uuid
        String uuid = refreshToken.substring(14);

        //check refreshToken validity
        if(isExpired(uuid))
            throw new InvalidRefreshToken("refreshToken Expired");

        log.info("Refresh Token Validity Check Passed");

        return refreshTokenRepository.findAuthTableByRefreshToken(uuid);
    }

}
