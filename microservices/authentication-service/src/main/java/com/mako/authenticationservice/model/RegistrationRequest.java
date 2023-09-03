package com.mako.authenticationservice.model;

import com.mako.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @Valid
    private UserDTO user;
    private UserCredential credential;

}
