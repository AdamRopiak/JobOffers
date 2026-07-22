package pl.joboffers.domain.joboffers;

import lombok.RequiredArgsConstructor;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

@RequiredArgsConstructor
class JobOfferRetriever {

    private final JobOfferRepository jobOfferRepository;

    List<JobOfferDto> findAllJobsOffer() {
        return jobOfferRepository.findAllJobOffers()
                .stream()
                .map(jobOffer -> JobOfferDto.builder()
                        .url(jobOffer.url())
                        .jobName(jobOffer.jobName())
                        .salary(jobOffer.salary())
                        .build())
                .toList();
    }

    JobOfferDto findJobOfferById(String jobId) {
        return jobOfferRepository.findJobOfferById(jobId)
                .map(offer -> new JobOfferDto(offer.jobId(), offer.url(), offer.jobName(), offer.company(), offer.salary() ))
                .orElseThrow(()->new JobOfferNotFoundException("Job offer not found"));
    }

    public boolean existJobOfferByUrl(String url) {
        return jobOfferRepository.existJobOfferByUrl(url);
    }
}
