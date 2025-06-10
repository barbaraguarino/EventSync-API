package com.uff.eventsync.application.user.service;

import com.uff.eventsync.application.user.dto.UserCreateRequestDTO;

public interface UserService {

    void createUser(UserCreateRequestDTO userData);

}
