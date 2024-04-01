package com.redstevo.code.Services;

import com.redstevo.code.CustomExceptions.UserDoesNotExistException;
import com.redstevo.code.Repositories.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Getting user");
        return authRepository.findByUsername(username).orElseThrow(
                () -> new UserDoesNotExistException("Incorrect username.")
        );
    }
}
