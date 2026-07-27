package pl.joboffers.domain.userloginandregistration;

import pl.joboffers.domain.userloginandregistration.dto.NewUserRequestDto;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;
import pl.joboffers.domain.userloginandregistration.dto.UserDto;

import java.util.UUID;

public class UserLoginAndRegistrationMapper {

    public static RegistrationResultDto mapFromNewUserRequestDtoToRegistrationResultDto(User user){
        return RegistrationResultDto.builder()
                .userId(UUID.randomUUID().toString())
                .userName(user.userName())
                .build();
    }

    public static UserDto mapFromUserToUserDto(User user){
        return UserDto.builder()
                .userName(user.userName())
                .build();
    }

    public static User mapFromNewUserRequestDtoToUser(NewUserRequestDto newUserRequestDto){
        return User.builder()
                .userId(UUID.randomUUID().toString())
                .userName(newUserRequestDto.userName())
                .password(newUserRequestDto.password())
                .build();
    }
}
