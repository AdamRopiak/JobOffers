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

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
@Component
public class JwtAuthenticator {

    private final AuthenticationManager authenticationManager;
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
        String secretKey = "fu34f34f3f3f3fdf";
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant creationTime = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        Instant expireAt = creationTime.plusSeconds(3600);
        String issuer = "JobOfferBacked";
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(creationTime)
                .withExpiresAt(expireAt)
                .withIssuer(issuer)
                .sign(algorithm);

    }
}

