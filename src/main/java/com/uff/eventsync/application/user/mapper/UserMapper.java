package com.uff.eventsync.application.user.mapper;

import com.uff.eventsync.application.user.dto.UserCreateRequestDTO;
import com.uff.eventsync.domain.user.entity.User;

public class UserMapper {

    public static User toEntity(UserCreateRequestDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }
}
