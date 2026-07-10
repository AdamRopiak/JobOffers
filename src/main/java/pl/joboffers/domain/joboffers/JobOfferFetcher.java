package pl.joboffers.domain.joboffers;

import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

interface JobOfferFetcher {
    List<JobOfferResponseDto> fetchAllJobOffers();
}
