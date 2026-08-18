package pl.joboffers.domain.joboffers;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;
import pl.joboffers.infrastructure.joboffers.controller.JobOfferRequestDto;

import java.util.List;

@AllArgsConstructor
public class JobOfferFacade {

    private final JobOfferRetriever jobOfferRetriever;
    private final JobOfferAdder jobOfferAdder;
    private final JobOfferFetcherService jobOfferFetcherService;

    public List<JobOfferDto> findAllJobsOffers(){
        return jobOfferRetriever.findAllJobsOffer();
    }

    public JobOfferDto findJobOfferById(String jobId) {
        return jobOfferRetriever.findJobOfferById(jobId);
    }

    public boolean existsByUrl(String url) {
        return jobOfferRetriever.existsJobOfferByUrl(url);
    }

    public List<JobOfferResponseDto> fetchAllJobOffersAndSave() {
        return jobOfferFetcherService.fetchAllJobOffersAndSave();
    }

    public JobOfferDto saveNewJobOffer(JobOfferRequestDto jobRequest) {
        return jobOfferAdder.saveNewJobOffer(jobRequest);
    }
}
