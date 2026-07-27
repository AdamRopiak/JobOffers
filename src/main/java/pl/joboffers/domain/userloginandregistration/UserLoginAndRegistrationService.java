package pl.joboffers.domain.userloginandregistration;

import lombok.RequiredArgsConstructor;
import pl.joboffers.domain.userloginandregistration.dto.NewUserRequestDto;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;
import pl.joboffers.domain.userloginandregistration.dto.UserDto;

import java.util.UUID;

@RequiredArgsConstructor
public class UserLoginAndRegistrationService {

    private final UserRepository userRepository;


    public RegistrationResultDto registerNewuser(NewUserRequestDto newUser) {
        User user = UserLoginAndRegistrationMapper.mapFromNewUserRequestDtoToUser(newUser);
        User savedUser = userRepository.save(user);
        return UserLoginAndRegistrationMapper.mapFromNewUserRequestDtoToRegistrationResultDto(savedUser);
    }

    public UserDto findUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .map(user -> new UserDto(user.userName()))
                .orElseThrow(()->new UserNotFoundException("User not found"));
    }
}
