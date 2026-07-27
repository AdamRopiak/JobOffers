package pl.joboffers.domain.joboffers;


import lombok.Getter;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

@Getter
public class JobOfferFacadeTestConfiguration {

    private final InMemoryJobOfferFetcher offerFetcher;
    private final InMemoryJobOfferRepository offerRepository;


    public JobOfferFacadeTestConfiguration() {
        this.offerFetcher=new InMemoryJobOfferFetcher();
        this.offerRepository = new InMemoryJobOfferRepository();
    }

    public JobOfferFacadeTestConfiguration(InMemoryJobOfferFetcher offerFetcher, InMemoryJobOfferRepository offerRepository) {
        this.offerFetcher = offerFetcher;
        this.offerRepository = offerRepository;
    }

    public JobOfferFacadeTestConfiguration(List<JobOfferResponseDto> remoteHttpOffers){
        this.offerFetcher = new InMemoryJobOfferFetcher(remoteHttpOffers);
        this.offerRepository = new InMemoryJobOfferRepository();
    }

    JobOfferFacade jobOfferFacadeForTests(){
        JobOfferAdder offerAdder = new JobOfferAdder(offerRepository);
        JobOfferRetriever offerRetriever = new JobOfferRetriever(offerRepository);
        JobOfferFetcherService offerFetcherService = new JobOfferFetcherService(offerFetcher, offerRepository);
        return new JobOfferFacade(offerRetriever, offerAdder, offerFetcherService);
    }



}
