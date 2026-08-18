package pl.joboffers.domain.joboffers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends MongoRepository<JobOffer, String> {
    List<JobOffer> findAll();

    boolean existsJobOfferByOfferUrl(String url);
}
