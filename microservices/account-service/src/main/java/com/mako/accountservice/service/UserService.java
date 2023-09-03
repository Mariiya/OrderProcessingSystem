package com.mako.accountservice.service;

import com.mako.accountservice.exception.AccountNotFoundException;
import com.mako.accountservice.model.User;

import java.math.BigInteger;

public interface UserService {
    User getUserByEmail(String email) throws AccountNotFoundException;

    User getUserById(BigInteger id) throws AccountNotFoundException;

    User save(User user);

    boolean isUserPresent(User user);

    User updateUser(User user) throws AccountNotFoundException;

    void deleteUser(User user) throws AccountNotFoundException;
}
