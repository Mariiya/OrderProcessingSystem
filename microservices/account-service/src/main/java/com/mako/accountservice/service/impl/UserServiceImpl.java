package com.mako.accountservice.service.impl;

import com.mako.accountservice.exception.UserNotFoundException;
import com.mako.accountservice.kafka.AccountActionsProducer;
import com.mako.accountservice.model.PasswordReset;
import com.mako.accountservice.repository.PasswordResetRepository;
import com.mako.accountservice.repository.UserRepository;
import com.mako.accountservice.model.User;
import com.mako.accountservice.service.UserService;
import com.mako.accountservice.utils.Converter;
import com.mako.dto.EventType;
import com.mako.event.PasswordChangeEvent;
import com.mako.utils.EventHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordResetRepository resetRepository;
    private final AccountActionsProducer kafkaProducer;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordResetRepository resetRepository, AccountActionsProducer kafkaProducer) {
        this.userRepository = userRepository;
        this.resetRepository = resetRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public User getUser(Long id) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User %s not found.", id));
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
    public Long passwordResetRequest(PasswordReset data) {
        String token = UUID.randomUUID().toString().substring(0, 10);
        LOGGER.info("token: " + token);
        User user = userRepository.findByEmail(data.getUser().getEmail());

        data.setToken(token);
        data.setExpirationDate(LocalDateTime.now().plusMinutes(2));
        data.setUser(user);
        data = resetRepository.save(data);

        kafkaProducer.sendPasswordChangeMessage(getPasswordChangeEvent(data));

        return data.getId();
    }

    @Override
    public boolean passwordReset(PasswordReset data) throws UserNotFoundException {
        Optional<PasswordReset> resetData = resetRepository.findById(data.getId());
        if (resetData.isPresent()) {
            PasswordReset ps = resetData.get();
            User user = userRepository.findByEmail(data.getUser().getEmail());
            if (ps.getToken().equals(data.getToken()) &&
                    ps.getExpirationDate().isAfter(LocalDateTime.now().minusMinutes(2))) {
                user.setPassword(data.getNewPassword());
                save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        User updated = getUser(user.getId());
        updated.setEmail(user.getEmail());
        updated.setFirstName(updated.getFirstName());
        updated.setLastName(updated.getLastName());
        return save(updated);
    }

    @Override
    public void deleteUser(User user) throws UserNotFoundException {
        if (!isUserPresent(user)) {
            throw new UserNotFoundException(String.format("User %s not found.", user.getId()));
        }
        userRepository.delete(user);
    }

    private PasswordChangeEvent getPasswordChangeEvent(PasswordReset data) {
        PasswordChangeEvent event = new PasswordChangeEvent();
        event.setPasswordChangeDTO(Converter.convertToPasswordChangeDTO(data));
        event.setEventType(EventType.RESET_PASSWORD);
        event.setCorrelationId(EventHelper.generateCorrelationId());
        return event;
    }
}
