package pl.joboffers.infrastructure.userloginandregistration.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.joboffers.domain.userloginandregistration.UserLoginAndRegistrationFacade;
import pl.joboffers.domain.userloginandregistration.dto.NewUserRequestDto;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;

@RestController
@AllArgsConstructor
public class RegisterController {

    private final UserLoginAndRegistrationFacade userLoginAndRegistrationFacade;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResultDto> registerNewUser(@RequestBody @Valid NewUserRequestDto newUserRequestDto){
        String encodedPassword = passwordEncoder.encode(newUserRequestDto.password());
        RegistrationResultDto registrationResultDto = userLoginAndRegistrationFacade
                .registerNewUser(new NewUserRequestDto(
                                    newUserRequestDto.userName(),
                                    encodedPassword));
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationResultDto);
    }
}
