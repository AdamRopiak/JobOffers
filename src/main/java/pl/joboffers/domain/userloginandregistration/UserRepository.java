package pl.joboffers.domain.userloginandregistration;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUserName(String userName);
}
