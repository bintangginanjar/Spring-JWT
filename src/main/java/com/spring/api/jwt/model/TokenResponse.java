package com.spring.api.jwt.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {

    private String token;

    private final String tokenType = "Bearer ";

}
