package pl.joboffers.domain.joboffers;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

@AllArgsConstructor
public class JobOfferFetcherService {

    private final JobOfferFetcher jobOfferFetcher;
    private final JobOfferRepository jobOfferRepository;

    List<JobOfferResponseDto> fetchAllJobOffersAndSave() {
        List<JobOfferResponseDto> fetchedJobOffers = jobOfferFetcher.fetchAllJobOffers();
        return fetchedJobOffers.stream()
                .filter(fetched -> !jobOfferRepository.existJobOfferByUrl(fetched.url()))
                .map(JobOfferMapper::mapFromJobOfferResponseDtoToJobOffer)
                .map(jobOfferRepository::save)
                .map(JobOfferMapper::mapFromJobOfferToJobOfferResponseDto)
                .toList();
    }
}
