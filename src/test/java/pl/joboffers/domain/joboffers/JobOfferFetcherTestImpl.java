package pl.joboffers.domain.joboffers;

import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

class JobOfferFetcherTestImpl implements JobOfferFetcher {

    @Override
    public List<JobOfferResponseDto> fetchAllJobOffers() {
        return List.of();
    }
}