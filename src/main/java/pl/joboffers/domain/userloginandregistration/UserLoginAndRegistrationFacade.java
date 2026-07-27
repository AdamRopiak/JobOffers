package pl.joboffers.domain.userloginandregistration;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.userloginandregistration.dto.NewUserRequestDto;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;
import pl.joboffers.domain.userloginandregistration.dto.UserDto;

import java.util.UUID;

@AllArgsConstructor
public class UserLoginAndRegistrationFacade {

    private final UserLoginAndRegistrationService userService;

    public RegistrationResultDto registerNewUser(NewUserRequestDto newUser) {
        return userService.registerNewuser(newUser);
    }

    public UserDto findUserByUserName(String userName) {
        return userService.findUserByUserName(userName);
    }
}
