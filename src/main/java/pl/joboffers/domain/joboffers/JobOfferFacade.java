package pl.joboffers.domain.joboffers;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;
import pl.joboffers.domain.joboffers.dto.JobOfferRequestDto;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class JobOfferFacade {

    private final JobOfferRepository jobOfferRepository;
    private final JobOfferFetcher jobOfferFetcher;

    public List<JobOfferDto> findAllJobsOffers(){
        return jobOfferRepository.findAllJobOffers()
                .stream()
                .map(jobOffer -> JobOfferDto.builder()
                        .url(jobOffer.url())
                        .jobName(jobOffer.jobName())
                        .salary(jobOffer.salary())
                        .build())
                .toList();
    }

    public JobOfferResponseDto saveNewJobOffer(JobOfferRequestDto jobRequest) {
        UUID jobId = UUID.randomUUID();
        JobOffer jobOffer = JobOffer.builder()
                .jobId(jobId.toString())
                .url(jobRequest.url())
                .jobName(jobRequest.jobName())
                .company(jobRequest.company())
                .salary(jobRequest.salary())
                .build();
        JobOffer savedJobOffer = jobOfferRepository.save(jobOffer);
        return new JobOfferResponseDto(savedJobOffer.jobId(), savedJobOffer.url(), savedJobOffer.jobName(), savedJobOffer.company(), savedJobOffer.salary());
    }

    public JobOfferResponseDto findJobOfferById(String jobId) {
        return jobOfferRepository.findJobOfferById(jobId)
                .map(offer -> new JobOfferResponseDto(offer.jobId(), offer.url(), offer.jobName(), offer.company(), offer.salary() ))
                .orElseThrow(()->new JobOfferNotFoundException("Job offer not found"));
    }

    public boolean existsByUrl(String url) {
        return jobOfferRepository.existJobOfferByUrl(url);
    }

    public List<JobOfferResponseDto> fetchAllJobOffersAndSave() {
        List<JobOfferResponseDto> fetchedJobOffers = jobOfferFetcher.fetchAllJobOffers();
        return fetchedJobOffers.stream()
                .filter(fetched -> !jobOfferRepository.existJobOfferByUrl(fetched.url()))
                .map(fetched -> {
                    JobOffer save = jobOfferRepository.save(new JobOffer(
                            fetched.jobId(),
                            fetched.url(),
                            fetched.jobName(),
                            fetched.company(),
                            fetched.salary()
                    ));
                    return new JobOfferResponseDto(save.jobId(), save.url(), save.jobName(), save.company(), save.salary());
                })
                .toList();
    }
}
