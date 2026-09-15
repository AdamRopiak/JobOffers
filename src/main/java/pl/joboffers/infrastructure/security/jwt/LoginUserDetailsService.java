package pl.joboffers.infrastructure.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.joboffers.domain.userloginandregistration.UserLoginAndRegistrationFacade;
import pl.joboffers.domain.userloginandregistration.dto.UserDto;

import java.util.Collections;

@AllArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final UserLoginAndRegistrationFacade loginAndRegistrationFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userByUserName = loginAndRegistrationFacade.findUserByUserName(username);
        return getUser(userByUserName);
    }

    private org.springframework.security.core.userdetails.User getUser(UserDto userDto){
        return new org.springframework.security.core.userdetails.User(
                userDto.userName(),
                userDto.password(),
                Collections.emptyList()
        );
    }
}
