package com.redstevo.code.Filters;

import com.redstevo.code.CustomExceptions.InvalidRequestException;
import com.redstevo.code.Repositories.TokensRepository;
import com.redstevo.code.Tables.TokensTable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutFilter implements LogoutHandler {

    private final TokensRepository tokensRepository;
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


    }
}
