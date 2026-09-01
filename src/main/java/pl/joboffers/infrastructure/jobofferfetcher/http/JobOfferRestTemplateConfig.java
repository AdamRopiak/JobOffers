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
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler,
                                     @Value("${joboffers.jobfetcher.http.client.config.connectionTimeOut:5000}") long connectionTimeout,
                                     @Value("${joboffers.jobfetcher.http.client.config.connectionReadOut:5000}") long readTimeout) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public JobOfferFetcher remoteJobOfferClient(RestTemplate restTemplate,
                                                       @Value("${joboffers.jobfetcher.http.client.config.uri}") String uri) {
        return new JobOfferFetcherRestTemplate(restTemplate, uri);
    }
}
