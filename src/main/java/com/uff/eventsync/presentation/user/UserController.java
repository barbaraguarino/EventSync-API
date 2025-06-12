package com.uff.eventsync.presentation.user;

import com.uff.eventsync.application.user.dto.AuthenticationResultDTO;
import com.uff.eventsync.application.user.dto.LoginRequestDTO;
import com.uff.eventsync.application.user.dto.LoginResponseDTO;
import com.uff.eventsync.application.user.dto.UserCreateRequestDTO;
import com.uff.eventsync.application.user.service.AuthenticationService;
import com.uff.eventsync.application.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserCreateRequestDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginData, HttpServletResponse httpServletResponse) {
        AuthenticationResultDTO authResult = authenticationService.login(loginData);

        Cookie cookie = new Cookie("accessToken", authResult.token());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(5 * 60 * 60);
        httpServletResponse.addCookie(cookie);

        var responseBody = new LoginResponseDTO("Login successful!", authResult.email(), authResult.name());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseBody);
    }
}
