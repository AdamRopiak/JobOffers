package pl.joboffers.domain.userloginandregistration;

import pl.joboffers.domain.userloginandregistration.dto.NewUserRequestDto;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class inMemoryUserRepository implements UserRepository{

    Map<String, User> database = new ConcurrentHashMap<>();

    @Override
    public User save(User newUser) {
        User user = new User(
                newUser.userId(),
                newUser.userName(),
                newUser.password()
        );
        database.put(user.userName(), user);
        return user;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return Optional.ofNullable(database.get(userName));
    }

}
