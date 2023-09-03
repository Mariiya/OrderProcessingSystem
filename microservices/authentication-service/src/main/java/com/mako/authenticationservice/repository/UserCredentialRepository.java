package com.mako.authenticationservice.repository;

import com.mako.authenticationservice.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, BigInteger> {

    Optional<UserCredential> findByUsername(String userName);

}
