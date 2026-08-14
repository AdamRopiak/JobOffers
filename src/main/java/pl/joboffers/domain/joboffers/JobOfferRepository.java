package pl.joboffers.domain.joboffers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface JobOfferRepository extends MongoRepository<JobOffer, String> {
    List<JobOffer> findAllJobOffers();

    boolean existJobOfferByUrl(String url);
}
