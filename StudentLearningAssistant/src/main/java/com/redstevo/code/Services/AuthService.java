package com.redstevo.code.Services;

import com.redstevo.code.Models.AuthRequestModel;
import com.redstevo.code.Models.AuthResponseModel;
import com.redstevo.code.Repositories.AuthRepository;
import com.redstevo.code.Repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;

    private final AuthRepository authRepository;

    private final ProfileRepository profileRepository;

    public ResponseEntity<AuthResponseModel> register(AuthRequestModel requestModel) {
        log.info("Processing the request");


        return null;
    }
}
