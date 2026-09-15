package pl.joboffers.infrastructure.userloginandregistration.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.joboffers.infrastructure.security.jwt.JwtAuthenticator;
import pl.joboffers.infrastructure.userloginandregistration.controller.dto.JwtTokenResponseDto;
import pl.joboffers.infrastructure.userloginandregistration.controller.dto.LoginRequestDto;

@RestController
@AllArgsConstructor
public class TokenController {

    private final JwtAuthenticator jwtAuthenticator;

    @PostMapping("/token")
    public ResponseEntity<JwtTokenResponseDto> authenticateAndGenerateToken(@RequestBody @Valid LoginRequestDto loginRequestDto){
        JwtTokenResponseDto jwtTokenResponseDto = jwtAuthenticator.authenticateAndGenerateToken(loginRequestDto);
        return ResponseEntity.ok(jwtTokenResponseDto);
    }

}
