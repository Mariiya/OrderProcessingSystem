package com.mako.authenticationservice.controller;

import com.mako.authenticationservice.exception.UserNotFoundException;
import com.mako.authenticationservice.model.AuthRequest;
import com.mako.authenticationservice.model.PasswordReset;
import com.mako.authenticationservice.model.RegistrationRequest;
import com.mako.authenticationservice.service.AuthService;
import com.mako.authenticationservice.service.UserDataService;
import com.mako.dto.UserDTO;
import jakarta.validation.Valid;
import org.apache.kafka.common.errors.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;

@RestController
@RequestMapping("/authentication-service")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDataService userService;

    @Autowired
    public AuthenticationController(AuthService authService, AuthenticationManager authenticationManager,
                                    UserDataService userService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registration(@RequestBody @Valid RegistrationRequest request) {
        authService.save(request);
        LOGGER.info(String.format("New user registered %s", request.getUser()));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(request.getUser().getEmail())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping("/token")
    public String getToken(@RequestBody AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (auth.isAuthenticated()) {
            return authService.generateToken(request.getUsername());
        }
        throw new RuntimeException("Invalid credentials");
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
       LOGGER.info("Validate token started");
        try {
            authService.validateToken(token);
        } catch (Exception e) {
            LOGGER.info("Token is NOT valid. " + e.getMessage());
            return ResponseEntity.status(403).build();
        }
        LOGGER.info("Token is valid.");
        return ResponseEntity.ok("Token is valid.");
    }

    @PostMapping("/request-password-reset")
    public ResponseEntity<BigInteger> resetPasswordRequest(@RequestBody PasswordReset data) throws UserNotFoundException {
        return ResponseEntity.ok(userService.passwordResetRequest(data));
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody @Valid PasswordReset data) throws UserNotFoundException {
        boolean result = userService.passwordReset(data);
        if (!result) {
            throw new InvalidRequestException("Request data is invalid.");
        }
    }
}
