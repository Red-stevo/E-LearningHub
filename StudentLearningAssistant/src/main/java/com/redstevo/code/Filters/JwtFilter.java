package com.redstevo.code.Filters;

import com.redstevo.code.CustomExceptions.InvalidAccessTokenException;
import com.redstevo.code.Services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        /*Checking the request header*/
        log.info("Checking the request header");

        String requestHeader = request.getHeader("Authorization");

        try {
            /*Checking if the header is empty*/
            if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
                log.warn("Request Does Not Contain A jwt Forwarding it to the next filter");
                filterChain.doFilter(request, response);
                return;
            }

            log.info("Bearer token present");

            /*Getting the jwt*/
            String jwt = requestHeader.substring(7);

            /*Getting the username*/
            String username = jwtService.getUsername(jwt);

            /*Check if the jwt has been corrupted*/
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.info("Bearer token extracted");

                /*Getting the AuthTable by user*/
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.isValid(userDetails, jwt)) {

                    UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    log.info("set the user details.");

                    SecurityContextHolder.getContext().setAuthentication(token);

                }
                filterChain.doFilter(request, response);
            }


            /*In this part of the code I am catching most of the run time exception that are thrown by the security
             filters and forwarding them to the ControllerAdvice for handling (simply set)*/

        }catch (MalformedJwtException exception){
            log.error("MalformedJwtException");
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }catch (SignatureException exception){
            log.error("SignatureException");
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }catch (ExpiredJwtException exception){
            log.error("ExpiredJwtException");
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }catch (BadCredentialsException exception){
            log.error("BadCredentialsException");
            handlerExceptionResolver.resolveException(request,response,null, exception);
        }catch (InternalAuthenticationServiceException exception) {
            log.error("InternalAuthenticationServiceException");
            handlerExceptionResolver.resolveException(request,response,null, exception);
        }catch (InvalidAccessTokenException exception){
            log.error("InvalidAccessTokenException");
            handlerExceptionResolver.resolveException(request,response, null, exception);
        } catch(Exception exception){
            log.error(exception.getClass().toString());
            handlerExceptionResolver.resolveException(request,response,null, exception);
        }
    }
}