package pl.joboffers.domain.joboffers;

import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

public interface JobOfferFetcher {
    List<JobOfferResponseDto> fetchAllJobOffers();
}
