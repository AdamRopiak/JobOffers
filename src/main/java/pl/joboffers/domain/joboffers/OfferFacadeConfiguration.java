package pl.joboffers.domain.joboffers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class OfferFacadeConfiguration {

    @Bean
    JobOfferFacade offerFacade(JobOfferFetcher jobOfferFetcher) {
        JobOfferRepository repo = new JobOfferRepository() {
            @Override
            public List<JobOffer> findAllJobOffers() {
                return List.of();
            }

            @Override
            public JobOffer save(JobOffer jobOffer) {
                return null;
            }

            @Override
            public Optional<JobOffer> findJobOfferById(String jobId) {
                return Optional.empty();
            }

            @Override
            public boolean existJobOfferByUrl(String url) {
                return false;
            }
        };
        JobOfferAdder offerAdder = new JobOfferAdder(repo);
        JobOfferRetriever offerRetriever = new JobOfferRetriever(repo);
        JobOfferFetcherService offerFetcher = new JobOfferFetcherService(jobOfferFetcher, repo);
        return new JobOfferFacade(offerRetriever, offerAdder, offerFetcher);
    }
}
