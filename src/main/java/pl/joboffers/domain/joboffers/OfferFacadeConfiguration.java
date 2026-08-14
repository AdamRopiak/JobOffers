package pl.joboffers.domain.joboffers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class OfferFacadeConfiguration {

    private final JobOfferRepository jobOfferRepository;

    @Bean
    JobOfferFacade offerFacade(JobOfferFetcher jobOfferFetcher) {

        JobOfferAdder offerAdder = new JobOfferAdder(jobOfferRepository);
        JobOfferRetriever offerRetriever = new JobOfferRetriever(jobOfferRepository);
        JobOfferFetcherService offerFetcher = new JobOfferFetcherService(jobOfferFetcher, jobOfferRepository);
        return new JobOfferFacade(offerRetriever, offerAdder, offerFetcher);
    }
}
