package com.uff.eventsync.application.user.service;

import com.uff.eventsync.application.user.dto.LoginRequestDTO;

public interface AuthenticationService {

    String login(LoginRequestDTO loginRequest);
}
