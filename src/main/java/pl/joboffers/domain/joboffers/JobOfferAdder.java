package pl.joboffers.domain.joboffers;

import lombok.RequiredArgsConstructor;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;
import pl.joboffers.domain.joboffers.dto.JobOfferRequestDto;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.UUID;

@RequiredArgsConstructor
class JobOfferAdder {

    private final JobOfferRepository jobOfferRepository;

    JobOfferDto saveNewJobOffer(JobOfferRequestDto jobRequest) {
        UUID jobId = UUID.randomUUID();
        JobOffer jobOffer = JobOffer.builder()
                .jobId(jobId.toString())
                .url(jobRequest.url())
                .jobName(jobRequest.jobName())
                .company(jobRequest.company())
                .salary(jobRequest.salary())
                .build();
        JobOffer savedJobOffer = jobOfferRepository.save(jobOffer);
        return new JobOfferDto(savedJobOffer.jobId(), savedJobOffer.url(), savedJobOffer.jobName(), savedJobOffer.company(), savedJobOffer.salary());
    }
}
