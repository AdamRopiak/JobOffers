package pl.joboffers.http.jobofferfetcher;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.joboffers.SampleJobOfferResponse;
import pl.joboffers.domain.joboffers.JobOfferFetcher;
import pl.joboffers.infrastructure.jobofferfetcher.http.JobOfferRestTemplateConfig;
import wiremock.org.apache.hc.core5.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;


import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.catchThrowable;

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

    @Test
    public void should_return_500_INTERNAL_SERVER_ERROR_when_fault_connection_reset_by_peer(){
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.CONNECTION_RESET_BY_PEER)));
        //when
        Throwable throwable = catchThrowable(() -> jobOfferFetcherClient.fetchAllJobOffers());
        //then
        assertThat(throwable).isInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");
    }

    @Test
    public void empty_response(){
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.EMPTY_RESPONSE)));
        //when
        Throwable throwable = catchThrowable(() -> jobOfferFetcherClient.fetchAllJobOffers());
        //then
        assertThat(throwable).isInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");

    }

    @Test
    public void should_return_204_NO_CONTET_and_body_with_four_offers_when_server_response_is_NO_CONTENT(){
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_NO_CONTENT)
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithFourOffersJson())));

        //when
        Throwable throwable = catchThrowable(() -> jobOfferFetcherClient.fetchAllJobOffers());
        //then
        assertThat(throwable).isInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("204 NO_CONTENT");
    }

    @Test
    public void should_return_500_INTERNAL_ERROR_when_response_delay_is_1500_ms(){
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithOneOfferJson())
                        .withFixedDelay(1500)));
        //when
        Throwable throwable = catchThrowable(() -> jobOfferFetcherClient.fetchAllJobOffers());
        //then
        assertThat(throwable).isInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR");
    }

    @Test
    public void should_return_404_NOT_FOUND_exception_when_http_status_return_not_found_status(){
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_NOT_FOUND)
                        .withHeader("Content-Type", "application/json")));
        //when
        Throwable throwable = catchThrowable(() -> jobOfferFetcherClient.fetchAllJobOffers());
        //then
        assertThat(throwable).isInstanceOf(ResponseStatusException.class);
        assertThat(throwable.getMessage()).isEqualTo("404 NOT_FOUND");
    }
}
