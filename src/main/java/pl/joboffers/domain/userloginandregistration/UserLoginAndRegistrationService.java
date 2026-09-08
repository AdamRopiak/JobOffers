package pl.joboffers.domain.userloginandregistration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.joboffers.domain.userloginandregistration.dto.NewUserRequestDto;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;
import pl.joboffers.domain.userloginandregistration.dto.UserDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserLoginAndRegistrationService {

    private final UserRepository userRepository;


    public RegistrationResultDto registerNewuser(NewUserRequestDto newUser) {
        User user = UserLoginAndRegistrationMapper.mapFromNewUserRequestDtoToUser(newUser);
        User savedUser = userRepository.save(user);
        return UserLoginAndRegistrationMapper.mapFromNewUserRequestDtoToRegistrationResultDto(savedUser);
    }

    public UserDto findUserByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .map(user -> new UserDto(user.userName(), user.getPassword()))
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
