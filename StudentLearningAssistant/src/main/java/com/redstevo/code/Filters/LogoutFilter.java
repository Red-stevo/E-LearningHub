package com.redstevo.code.Filters;

import com.redstevo.code.CustomExceptions.InvalidRefreshToken;
import com.redstevo.code.CustomExceptions.InvalidRequestException;
import com.redstevo.code.Repositories.RefreshTokenRepository;
import com.redstevo.code.Repositories.TokensRepository;
import com.redstevo.code.Tables.TokensTable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutFilter implements LogoutHandler {

    private final TokensRepository tokensRepository;

    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {

        /*Extract the request header if present.*/

        String requestHeader = request.getHeader("Authorization");

        if(requestHeader == null || !requestHeader.startsWith("Bearer ")) return;

        /*Extract the token*/
        String token = requestHeader.substring(7);

        /*Get the token from the database.*/
        TokensTable tokensTable = tokensRepository.findByToken(token).orElseThrow(
                () -> new InvalidRequestException("Invalid Token Passed")
        );

        /*Making token as logged out*/
        tokensTable.setIsLoggedOut(true);

        /*updating the token status in the database.*/
        tokensRepository.save(tokensTable);



        /*the refresh_token will also be nullified when a user logs out*/
        String refreshToken = request.getHeader("cookie");
        if(refreshToken == null || !refreshToken.startsWith("refresh_token="))
            throw new InvalidRefreshToken("RefreshToken Not passed or Illegally modified");

        /*Extract refreshToken UUID*/
        String uuid = refreshToken.substring(14);

        //delete the refreshToken for the user from the database.
        refreshTokenRepository.deleteByRefreshToken(uuid);

        response.addCookie(null);
    }
}
