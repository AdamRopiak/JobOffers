package pl.joboffers.domain.joboffers;

import lombok.RequiredArgsConstructor;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;
import pl.joboffers.infrastructure.joboffers.controller.JobOfferRequestDto;

@RequiredArgsConstructor
class JobOfferAdder {

    private final JobOfferRepository jobOfferRepository;

    JobOfferDto saveNewJobOffer(JobOfferRequestDto jobRequest) {
        JobOffer jobOffer = JobOfferMapper.mapFromJobOfferRequestDtoToJobOffer(jobRequest);
        JobOffer savedJobOffer = jobOfferRepository.save(jobOffer);
        return JobOfferMapper.mapFromJobOfferToJobOfferDto(savedJobOffer);
    }
}
