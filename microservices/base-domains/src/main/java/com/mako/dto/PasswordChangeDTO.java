package com.mako.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeDTO {
    private BigInteger id;
    private String userEmail;
    private String token;
    private String newPassword;
}
