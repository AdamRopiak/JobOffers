package pl.joboffers.http.jobofferfetcher;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.web.client.RestTemplate;
import pl.joboffers.SampleJobOfferResponse;
import pl.joboffers.domain.joboffers.JobOfferFetcher;
import pl.joboffers.infrastructure.jobofferfetcher.http.JobOfferRestTemplateConfig;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class JobOfferFetcherRestTemplateIntegrationTest extends JobOfferRestTemplateConfig implements SampleJobOfferResponse {


    @RegisterExtension
    public static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    public JobOfferFetcher jobOfferFetcher() {
        RestTemplate restTemplate = restTemplate(restTemplateResponseErrorHandler(), 1000, 1000);
        return remoteJobOfferClient(restTemplate, wireMockServer.baseUrl());
    }

    JobOfferFetcher jobOfferFetcherClient = jobOfferFetcher();

}
