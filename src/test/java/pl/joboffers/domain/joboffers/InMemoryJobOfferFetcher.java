package pl.joboffers.domain.joboffers;

import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

public class InMemoryJobOfferFetcher implements JobOfferFetcher {

    private final List<JobOfferResponseDto> mockedJobOffers;

    public InMemoryJobOfferFetcher(){
        this.mockedJobOffers = List.of(
                new JobOfferResponseDto("Senior Developer", "Javax", "15.234", "www.joboffer1.com"),
                new JobOfferResponseDto("Senior Java Developer", "Testowa firma", "150.234", "www.joboffer2.com"),
                new JobOfferResponseDto("Junior Java Developer", "FBI", "234.23", "www.joboffer3.com")
        );
    }

    public InMemoryJobOfferFetcher(List<JobOfferResponseDto> mockedJobOffers){
        this.mockedJobOffers=mockedJobOffers;
    }

    @Override
    public List<JobOfferResponseDto> fetchAllJobOffers() {
        return mockedJobOffers;
    }
}
