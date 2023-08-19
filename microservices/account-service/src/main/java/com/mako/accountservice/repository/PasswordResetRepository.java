package com.mako.accountservice.repository;

import com.mako.accountservice.model.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, BigInteger> {
}
