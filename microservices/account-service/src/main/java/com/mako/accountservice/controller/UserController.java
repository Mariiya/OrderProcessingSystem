package com.mako.accountservice.controller;

import com.mako.accountservice.exception.UserNotFoundException;
import com.mako.accountservice.model.PasswordReset;
import com.mako.accountservice.model.User;
import com.mako.accountservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> registration(@RequestBody @Valid User user) {
        User newUser = userService.save(user);
        LOGGER.info(String.format("New user registered %s", user));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException {
        userService.updateUser(user);
        LOGGER.info(String.format("User updated %s", user));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable @NotNull Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestBody User user) throws UserNotFoundException {
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request-password-reset")
    public void resetPasswordRequest(@RequestBody PasswordReset data) {
        userService.passwordResetRequest(data);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestBody @Valid PasswordReset data) throws UserNotFoundException {
        userService.passwordReset(data);
    }

}
