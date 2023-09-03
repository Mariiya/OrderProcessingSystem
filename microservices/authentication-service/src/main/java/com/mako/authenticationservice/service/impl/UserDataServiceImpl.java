package com.mako.authenticationservice.service.impl;

import com.mako.authenticationservice.exception.UserNotFoundException;
import com.mako.authenticationservice.kafka.AuthenticationProducer;
import com.mako.authenticationservice.model.PasswordReset;
import com.mako.authenticationservice.model.UserCredential;
import com.mako.authenticationservice.repository.PasswordResetRepository;
import com.mako.authenticationservice.repository.UserCredentialRepository;
import com.mako.authenticationservice.service.UserDataService;
import com.mako.authenticationservice.util.Converter;
import com.mako.dto.EventType;
import com.mako.dto.UserDTO;
import com.mako.event.PasswordChangeEvent;
import com.mako.event.UserEvent;
import com.mako.utils.EventHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDataServiceImpl implements UserDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDataServiceImpl.class);
    private final UserCredentialRepository credentialRepository;
    private final PasswordResetRepository resetRepository;
    private final AuthenticationProducer kafkaProducer;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataServiceImpl(UserCredentialRepository credentialRepository,
                               PasswordResetRepository resetRepository,
                               AuthenticationProducer kafkaProducer, PasswordEncoder passwordEncoder) {
        this.credentialRepository = credentialRepository;
        this.resetRepository = resetRepository;
        this.kafkaProducer = kafkaProducer;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public BigInteger passwordResetRequest(PasswordReset data) throws UserNotFoundException {
        String token = UUID.randomUUID().toString().substring(0, 10);
        LOGGER.info("token: " + token);
        Optional<UserCredential> user = credentialRepository.findByUsername(data.getUserEmail());
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("No such user %s", data.getUserEmail()));
        }

        data.setToken(token);
        data.setExpirationDate(LocalDateTime.now().plusMinutes(2));
        data.setNewPassword("default");
        data = resetRepository.save(data);

        kafkaProducer.sendPasswordChangeMessage(getPasswordChangeEvent(data));

        return data.getId();
    }

    @Override
    public boolean passwordReset(PasswordReset data) throws UserNotFoundException {
        Optional<PasswordReset> resetData = resetRepository.findById(data.getId());

        if (resetData.isPresent()) {

            PasswordReset ps = resetData.get();
            Optional<UserCredential> userCredential = credentialRepository.findByUsername(data.getUserEmail());

            if (userCredential.isEmpty()) {
                throw new UserNotFoundException(String.format("No such user %s", data.getUserEmail()));
            }

            if (ps.getExpirationDate().isAfter(LocalDateTime.now().minusMinutes(2)) &&
                    ps.getToken().equals(data.getToken())) {

                UserCredential cred = userCredential.get();
                cred.setPassword(passwordEncoder.encode(data.getNewPassword()));
                credentialRepository.save(cred);

                LOGGER.info(String.format("Credentials updated for user %s", cred.getUsername()));
                return true;
            }
        }

        return false;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        kafkaProducer.sendMessage(getUserEvent(userDTO));
    }

    private UserEvent getUserEvent(UserDTO user) {
        UserEvent event = new UserEvent();
        event.setUser(user);
        event.setEventType(EventType.CREATE_ACCOUNT);
        event.setCorrelationId(EventHelper.generateCorrelationId());
        return event;
    }

    private PasswordChangeEvent getPasswordChangeEvent(PasswordReset data) {
        PasswordChangeEvent event = new PasswordChangeEvent();
        event.setPasswordChangeDTO(Converter.convertToPasswordChangeDTO(data));
        event.setEventType(EventType.RESET_PASSWORD);
        event.setCorrelationId(EventHelper.generateCorrelationId());
        return event;
    }
}
