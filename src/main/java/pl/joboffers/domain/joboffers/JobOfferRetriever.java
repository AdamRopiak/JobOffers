package pl.joboffers.domain.joboffers;

import lombok.RequiredArgsConstructor;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;

import java.util.List;

@RequiredArgsConstructor
class JobOfferRetriever {

    private final JobOfferRepository jobOfferRepository;

    List<JobOfferDto> findAllJobsOffer() {
        return jobOfferRepository.findAll()
                .stream()
                .map(JobOfferMapper::mapFromJobOfferToJobOfferDto)
                .toList();
    }

    JobOfferDto findJobOfferById(String jobId) {
        return jobOfferRepository.findById(jobId)
                .map(JobOfferMapper::mapFromJobOfferToJobOfferDto)
                .orElseThrow(()->new JobOfferNotFoundException("Job offer not found"));
    }

    public boolean existsJobOfferByUrl(String url) {
        return jobOfferRepository.existsJobOfferByOfferUrl(url);
    }
}
