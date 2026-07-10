package pl.joboffers.domain.joboffers;

import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

public class InMemoryJobOfferFetcher implements JobOfferFetcher {

    private final List<JobOfferResponseDto> mockedJobOffers;

    public InMemoryJobOfferFetcher(){
        this.mockedJobOffers = List.of(
                new JobOfferResponseDto("1", "www.joboffer1.com", "Senior Developer", "Javax", 15.234D),
                new JobOfferResponseDto("2", "www.joboffer2.com", "Senior Java Developer", "Testowa firma", 150.234D),
                new JobOfferResponseDto("3", "www.joboffer3.com", "Junior Java Developer", "FBI", 234.23D)
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
