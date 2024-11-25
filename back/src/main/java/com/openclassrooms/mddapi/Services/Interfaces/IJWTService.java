package com.openclassrooms.mddapi.Services.Interfaces;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public interface IJWTService {

    String generateToken(String name);

    boolean validateToken(String token);

    JwtAuthenticationToken getContext(String token);
}
