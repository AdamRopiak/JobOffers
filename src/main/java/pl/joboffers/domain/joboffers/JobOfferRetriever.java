package pl.joboffers.domain.joboffers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;

import java.util.List;

@RequiredArgsConstructor
class JobOfferRetriever {

    private final JobOfferRepository jobOfferRepository;

    List<JobOfferDto> findAllJobsOffer() {
        return jobOfferRepository.findAllJobOffers()
                .stream()
                .map(JobOfferMapper::mapFromJobOfferToJobOfferDto)
                .toList();
    }

    JobOfferDto findJobOfferById(String jobId) {
        return jobOfferRepository.findJobOfferById(jobId)
                .map(JobOfferMapper::mapFromJobOfferToJobOfferDto)
                .orElseThrow(()->new JobOfferNotFoundException("Job offer not found"));
    }

    public boolean existJobOfferByUrl(String url) {
        return jobOfferRepository.existJobOfferByUrl(url);
    }
}
