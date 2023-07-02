package com.example.jwt.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
public class TokenDTO implements Serializable {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
