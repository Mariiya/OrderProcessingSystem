package com.mako.accountservice.service.impl;

import com.mako.accountservice.exception.AccountNotFoundException;
import com.mako.accountservice.repository.UserRepository;
import com.mako.accountservice.model.User;
import com.mako.accountservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(BigInteger id) throws AccountNotFoundException {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new AccountNotFoundException(String.format("User %s not found.", id));
        }
        return user.get();
    }

    @Override
    public User getUserByEmail(String email) throws AccountNotFoundException {

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new AccountNotFoundException(String.format("User %s not found.", email));
        }
        return user.get();
    }

    @Override
    public User save(User user) {
        User savedUser = userRepository.save(user);
        LOGGER.info(String.format("User saved, user id %s", user.getId()));
        return savedUser;
    }

    @Override
    public boolean isUserPresent(User user) {
        return userRepository.existsById(user.getId());
    }

    @Override
    public User updateUser(User user) throws AccountNotFoundException {
        User updated = getUserById(user.getId());
        updated.setEmail(user.getEmail());
        updated.setFirstName(updated.getFirstName());
        updated.setLastName(updated.getLastName());
        return save(updated);
    }

    @Override
    public void deleteUser(User user) throws AccountNotFoundException {
        if (!isUserPresent(user)) {
            throw new AccountNotFoundException(String.format("User %s not found.", user.getId()));
        }
        userRepository.delete(user);
    }

}
