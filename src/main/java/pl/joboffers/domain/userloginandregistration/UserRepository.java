package pl.joboffers.domain.userloginandregistration;

import java.util.Optional;

interface UserRepository {

    User save(User user);

    Optional<User> findByUserName(String userName);
}
