package pl.joboffers.infrastructure.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "auth.jwt")
public record JwtAuthenticationProperties (
        String secret,
        int expirationDays,
        String issuer
){
}
