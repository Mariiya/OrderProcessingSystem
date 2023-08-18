package com.mako.accountservice.service;

import com.mako.accountservice.exception.UserNotFoundException;
import com.mako.accountservice.model.PasswordReset;
import com.mako.accountservice.model.User;

public interface UserService {
    User getUser(Long id) throws UserNotFoundException;

    User save(User user);

    boolean isUserPresent(User user);

    void passwordResetRequest(PasswordReset data);

    boolean passwordReset(PasswordReset data) throws UserNotFoundException;

    User updateUser(User user) throws UserNotFoundException;

    void deleteUser(User user) throws UserNotFoundException;
}
