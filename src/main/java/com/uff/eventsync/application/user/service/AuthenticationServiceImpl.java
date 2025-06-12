package com.uff.eventsync.application.user.service;

import com.uff.eventsync.application.user.dto.AuthenticationResultDTO;
import com.uff.eventsync.application.user.dto.LoginRequestDTO;
import com.uff.eventsync.config.security.TokenService;
import com.uff.eventsync.domain.user.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public AuthenticationResultDTO login(LoginRequestDTO loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        );
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        var authenticatedUser = (User) auth.getPrincipal();
        String token = this.tokenService.generateToken(authenticatedUser);
        return new AuthenticationResultDTO(token, authenticatedUser.getEmail(), authenticatedUser.getName());
    }
}
