package pl.joboffers.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import pl.joboffers.JobOffersApplication;
import pl.joboffers.domain.joboffers.JobOfferFetcher;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = JobOffersApplication.class, properties = "scheduling.enabled=true")
public class JobOfferSchedulerTest {

    @SpyBean
    JobOfferFetcher jobOfferFetcher;

    @Test
    public void should_run_job_offers_fetching_exactly_given_times(){
        await().
                atMost(Duration.ofSeconds(2))
                .untilAsserted(() ->verify(jobOfferFetcher, times(1)).fetchAllJobOffers());
    }
}
