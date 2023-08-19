package com.mako.accountservice.repository;

import com.mako.accountservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface UserRepository extends JpaRepository<User, BigInteger> {
    User findByEmail(String email);
}
