package pl.joboffers.domain.userloginandregistration;

import pl.joboffers.domain.userloginandregistration.dto.NewUserRequestDto;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;

import java.util.Optional;

interface UserRepository {

    User save(User user);

    Optional<User> findByUserName(String userName);
}
