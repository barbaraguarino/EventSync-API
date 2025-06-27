package com.uff.eventsync.application.user.service;

import com.uff.eventsync.application.user.dto.AuthenticationResultDTO;
import com.uff.eventsync.application.user.dto.LoginRequestDTO;

public interface AuthenticationService {

    AuthenticationResultDTO login(LoginRequestDTO loginRequest);
}
