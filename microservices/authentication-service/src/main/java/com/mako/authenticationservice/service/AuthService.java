package com.mako.authenticationservice.service;

import com.mako.authenticationservice.model.RegistrationRequest;
import com.mako.authenticationservice.model.UserCredential;
import com.mako.authenticationservice.repository.UserCredentialRepository;
import com.mako.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDataService userDataService;

    @Autowired
    public AuthService(UserCredentialRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, UserDataService userDataService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userDataService = userDataService;
    }

    public void save(RegistrationRequest request) {
        UserDTO userDTO = request.getUser();
        UserCredential userCredential = request.getCredential();
        userCredential.setUsername(userDTO.getEmail());
        userDataService.createUser(userDTO);
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        repository.save(userCredential);
    }

    public String generateToken(String userName) {
        return jwtService.generateToken(userName);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}
