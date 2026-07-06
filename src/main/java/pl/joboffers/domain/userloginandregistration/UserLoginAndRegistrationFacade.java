package pl.joboffers.domain.userloginandregistration;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.userloginandregistration.dto.NewUserRequestDto;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;
import pl.joboffers.domain.userloginandregistration.dto.UserDto;

import java.util.UUID;

@AllArgsConstructor
public class UserLoginAndRegistrationFacade {

    private final UserRepository userRepository;

    public RegistrationResultDto registerNewUser(NewUserRequestDto newUser) {
        UUID userId = UUID.randomUUID();
        User user = User.builder()
                .userId(userId.toString())
                .userName(newUser.userName())
                .password(newUser.password())
                .build();
        User savedUser = userRepository.save(user);
        return new RegistrationResultDto(savedUser.userId(), savedUser.userName());
    }

    public UserDto findUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .map(user -> new UserDto(user.userName()))
                .orElseThrow(()->new UserNotFoundException("User not found"));


    }
}
