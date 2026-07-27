package pl.joboffers.domain.joboffers;

import java.util.List;
import java.util.Optional;

public interface JobOfferRepository {
    List<JobOffer> findAllJobOffers();

    JobOffer save(JobOffer jobOffer);

    Optional<JobOffer> findJobOfferById(String jobId);

    boolean existJobOfferByUrl(String url);
}
