package com.redstevo.code.Filters;

import com.redstevo.code.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        /*Checking the request header*/
        log.info("Checking the request header");

        String requestHeader = request.getHeader("Authorization");

        /*Checking if the header is empty*/
        if(requestHeader == null || requestHeader.startsWith("Bearer ")){
            log.warn("Request Does Not Contain A jwt Forwarding it to the next filter");
            filterChain.doFilter(request,response);
            return;
        }

        /*Getting the jwt*/
        String jwt = requestHeader.substring(7);

        /*Getting the username*/

    }
}
