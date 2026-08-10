package pl.joboffers.infrastructure.jobofferfetcher.http;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.joboffers.domain.joboffers.JobOfferFetcher;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;
import org.springframework.http.HttpHeaders;

import java.util.List;

@AllArgsConstructor
public class JobOfferFetcherRestTemplate implements JobOfferFetcher {

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;

    @Override
    public List<JobOfferResponseDto> fetchAllJobOffers() {
        String urlForService = getUrlForService("/offers");
        HttpHeaders headers = new HttpHeaders();
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        final String url = UriComponentsBuilder.fromHttpUrl(urlForService)
                .toUriString();

        ResponseEntity<List<JobOfferResponseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<JobOfferResponseDto>>() {
                });
        final List<JobOfferResponseDto> results = response.getBody();

        return results;
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}
