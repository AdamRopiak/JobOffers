package pl.joboffers.infrastructure.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import pl.joboffers.infrastructure.userloginandregistration.controller.dto.JwtTokenResponseDto;
import pl.joboffers.infrastructure.userloginandregistration.controller.dto.LoginRequestDto;

import java.time.*;

@AllArgsConstructor
@Component
public class JwtAuthenticator {

    private final AuthenticationManager authenticationManager;
    private final JwtAuthenticationProperties jwtAuthenticationProperties;
    private final Clock clock;

    public JwtTokenResponseDto authenticateAndGenerateToken(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.userName(), loginRequestDto.password()));
        User user = (User) authentication.getPrincipal();
        String token = createToken(user);
        String userName = user.getUsername();
        return JwtTokenResponseDto.builder()
                .userName(userName)
                .token(token)
                .build();
        }


    private String createToken(User user) {
        String secretKey = jwtAuthenticationProperties.secret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant creationTime = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expireAt = creationTime.plus(Duration.ofDays(jwtAuthenticationProperties.expirationDays()));
        String issuer = jwtAuthenticationProperties.issuer();
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(creationTime)
                .withExpiresAt(expireAt)
                .withIssuer(issuer)
                .sign(algorithm);

    }
}

