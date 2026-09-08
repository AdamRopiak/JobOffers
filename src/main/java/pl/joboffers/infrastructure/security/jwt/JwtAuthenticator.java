package pl.joboffers.infrastructure.security.jwt;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.joboffers.infrastructure.userloginandregistration.controller.dto.JwtTokenResponseDto;
import pl.joboffers.infrastructure.userloginandregistration.controller.dto.LoginRequestDto;

@AllArgsConstructor
@Component
public class JwtAuthenticator {

    private final AuthenticationManager authenticationManager;

    public JwtTokenResponseDto authenticateAndGenerateToken(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password()));
        return JwtTokenResponseDto.builder().build();
    }
}

