package com.mako.authenticationservice.repository;

import com.mako.authenticationservice.model.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, BigInteger> {
}
