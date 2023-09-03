package com.mako.authenticationservice.service;

import com.mako.authenticationservice.exception.UserNotFoundException;
import com.mako.authenticationservice.model.PasswordReset;
import com.mako.dto.UserDTO;

import java.math.BigInteger;

public interface UserDataService {

    BigInteger passwordResetRequest(PasswordReset data) throws UserNotFoundException;

    boolean passwordReset(PasswordReset data) throws UserNotFoundException;

    void createUser(UserDTO userDTO);
}
