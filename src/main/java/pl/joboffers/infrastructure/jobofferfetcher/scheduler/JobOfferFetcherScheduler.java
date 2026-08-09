package pl.joboffers.infrastructure.jobofferfetcher.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.joboffers.domain.joboffers.JobOfferFacade;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class JobOfferFetcherScheduler {

    private final JobOfferFacade jobOfferFacade;

    @Scheduled(cron = "${joboffers.fetcher.scheduler.joboffers.delay}")
    public List<JobOfferResponseDto> fetchJobOfferWithSchedulerFromRemote(){
        log.info("Scheduled offer fetching has started.");
        return jobOfferFacade.fetchAllJobOffersAndSave();
    }

}
