package pl.joboffers.feature;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.joboffers.BaseIntegrationTest;
import pl.joboffers.SampleJobOfferResponse;
import pl.joboffers.domain.joboffers.JobOfferFetcher;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;
import pl.joboffers.infrastructure.jobofferfetcher.scheduler.JobOfferFetcherScheduler;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTryToFetchNewJobOffersAndSavesInDatabaseTest extends BaseIntegrationTest implements SampleJobOfferResponse {

    @Autowired
    JobOfferFetcher jobOfferFetcher;
    @Autowired
    JobOfferFetcherScheduler jobOfferFetcherScheduler;

    @Test
    public void user_should_get_job_offers_from_external_server_and_add_new_to_local_databse_but_should_be_logged_and_external_server_should_have_offers() throws Exception {

   //step 1: scheduler ran 1st time and made GET to external server, server return empty array
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithFourOffersJson())));
        //when
        List<JobOfferResponseDto> emptyJobOffersList = jobOfferFetcherScheduler.fetchJobOfferWithSchedulerFromRemote();
        //then
        assertThat(emptyJobOffersList).isEmpty();
    /*step 2: user made GET /offers -- authentication will be added later in project (with no jwt token and system returned UNAUTHORIZED(401))
    step 3: user tried to get JWT token by requesting POST /token with username=User, password=Password and system returned UNAUTHORIZED(401)
    step 4: user made POST /register with username=User, password=Password and system registered user with status CREATED(201)
    step 5: user tried to get JWT token by requesting POST /token with username=User, password=Password and system returned OK(200) and jwttoken=AAAA.BBBB.CCC*/
    //step 6: user made GET /offers -- authentication will be added later in project (with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 0 offers)
        //given && when
        ResultActions getZeroOffers = mockMvc.perform(get("/offers")
                        .contentType(MediaType.APPLICATION_JSON));
        MvcResult getZeroOffersResult = getZeroOffers.andExpect(status().isOk()).andReturn();
        String getZeroOffersAsString = getZeroOffersResult.getResponse().getContentAsString();
        List<JobOfferResponseDto> offers = objectMapper.readValue(getZeroOffersAsString, new TypeReference<>() {
        });
        //then
        assertThat(offers).isEmpty();


        //step 7: there are 2 new offers in external HTTP server
    //step 8: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 0 and 1 to database
        //given
        wireMockServer.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithTwoOffersJson())));
        //when
        List<JobOfferResponseDto> twoJobOffersList = jobOfferFetcherScheduler.fetchJobOfferWithSchedulerFromRemote();
        //then
        assertThat(twoJobOffersList).hasSize(2);

    /*step 9: user made GET /offers -- authentication will be added later in project (with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers)
    step 10: user made GET /offers/9999 -- authentication will be added later in project (with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”)
    step 11: user made GET /offers/1 -- authentication will be added later in project (with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with offer)
    step 12: user tried to POST /offers  -- authentication will be added later in project (with no jwt token and system returned UNAUTHORIZED(401))
    step 13: user made POST /offers -- authentication will be added later in project (with header “Authorization: Bearer AAAA.BBBB.CCC” and body=someOfferRequestDto and system returned CREATED(201) with saved offer id 3)*/
        //given
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "title": "Junior DevOps Engineer",
                                "company": "CDQ Poland",
                                "salary": "8k - 14k PLN",
                                "offerUrl": "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd"
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON));
        //when & then
        MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        JobOfferResponseDto jobOfferResponseDto = objectMapper.readValue(contentAsString, JobOfferResponseDto.class);
        assertAll(
                () -> assertThat(jobOfferResponseDto.offerUrl()).isEqualTo("https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd"),
                () -> assertThat(jobOfferResponseDto.title()).isEqualTo("Junior DevOps Engineer")
        );



    /*step 14: user made GET /offers -- authentication will be added later in project (with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 3 offers with ids: 1, 2 and 3)
    step 15: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 4 and 5 to database
    step 16: user made GET /offers -- authentication will be added later in project (with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 5 offers with ids: 1, 2, 3, 4 and 5)*/

    /* ---- EXTRA STEP WITH CACHE ----
    step 17: scheduler ran within 60 minutes cache TTL interval and system retrieved offers from cache without calling external HTTP server
    step 18: 60 minutes passed, cache expired, and there are 2 new offers in external HTTP server
     */

    }
}