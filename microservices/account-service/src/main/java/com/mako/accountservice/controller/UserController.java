package com.mako.accountservice.controller;

import com.mako.accountservice.exception.AccountNotFoundException;

import com.mako.accountservice.model.User;
import com.mako.accountservice.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-service/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) throws AccountNotFoundException {
        User updated = userService.updateUser(user);
        LOGGER.info(String.format("User updated %s", updated));
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable @NotNull String email) throws AccountNotFoundException {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestBody User user) throws AccountNotFoundException {
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

}
