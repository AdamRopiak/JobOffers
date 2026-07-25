package pl.joboffers.domain.userloginandregistration;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository{

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
