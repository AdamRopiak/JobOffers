package pl.joboffers.infrastructure.jobofferfetcher.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.joboffers.domain.joboffers.JobOfferFetcher;

import java.time.Duration;

@Configuration
public class JobOfferRestTemplateConfig {
    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public JobOfferFetcher remoteJobOfferClient(RestTemplate restTemplate,
                                                       @Value("${joboffers.jobfetcher.http.client.config.uri}") String uri,
                                                       @Value("${joboffers.jobfetcher.http.client.config.port}") int port) {
        return new JobOfferFetcherRestTemplate(restTemplate, uri, port);
    }
}
