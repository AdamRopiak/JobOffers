package pl.joboffers.infrastructure.jobofferfetcher.http;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.joboffers.domain.joboffers.JobOfferFetcher;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class JobOfferFetcherRestTemplate implements JobOfferFetcher {

    private final String URL_ENDPOINT_PATH = "/offers";

    private final RestTemplate restTemplate;
    private final String uri;
    private final int port;



    @Override
    public List<JobOfferResponseDto> fetchAllJobOffers() {
        log.info("Fetching starts.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<List<JobOfferResponseDto>> response = getListResponseEntity(requestEntity);
            return getJobOfferResponseDto(response);
        } catch (ResourceAccessException error) {
            log.error("Error during fetching: " + error.getMessage());
            return Collections.emptyList();
        }
    }

    private static List<JobOfferResponseDto> getJobOfferResponseDto(ResponseEntity<List<JobOfferResponseDto>> response) {
        final List<JobOfferResponseDto> results = response.getBody();
        if (results == null) {
            log.info("Returned list is empty.");
            return Collections.emptyList();
        }
        log.info("Fetching completed.");
        return results;
    }

    private ResponseEntity<List<JobOfferResponseDto>> getListResponseEntity(HttpEntity<HttpHeaders> requestEntity) {
        String urlForService = getUrlForService(URL_ENDPOINT_PATH);
        final String url = UriComponentsBuilder.fromHttpUrl(urlForService)
                .toUriString();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<JobOfferResponseDto>>() {
                });
    }

    private String getUrlForService(String service) {
        return uri + ":" + port + service;
    }
}
