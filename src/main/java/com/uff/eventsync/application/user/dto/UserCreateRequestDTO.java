package com.uff.eventsync.application.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequestDTO(
    @NotBlank(message = "Name cannot be empty.")
    String name,

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Invalid email format.")
    String email,

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    String password
){}
