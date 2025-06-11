package com.uff.eventsync.application.user.service;

import com.uff.eventsync.application.user.dto.UserCreateRequestDTO;
import com.uff.eventsync.application.user.mapper.UserMapper;
import com.uff.eventsync.domain.user.entity.User;
import com.uff.eventsync.domain.user.repository.UserRepository;
import com.uff.eventsync.shared.exception.UserAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(UserCreateRequestDTO userDTO) {

        if (this.userRepository.existsByEmail(userDTO.email())) {
            throw new UserAlreadyExistsException("A user with this email already exists.");
        }

        User newUser = UserMapper.toEntity(userDTO);
        newUser.setPassword(passwordEncoder.encode(userDTO.password()));

        this.userRepository.save(newUser);
    }
}
