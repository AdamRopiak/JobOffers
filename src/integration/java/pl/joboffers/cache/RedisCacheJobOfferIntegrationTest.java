package pl.joboffers.cache;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import pl.joboffers.BaseIntegrationTest;
import pl.joboffers.domain.joboffers.JobOfferFacade;
import pl.joboffers.domain.userloginandregistration.dto.RegistrationResultDto;
import pl.joboffers.infrastructure.userloginandregistration.controller.dto.JwtTokenResponseDto;

import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

public class RedisCacheJobOfferIntegrationTest extends BaseIntegrationTest {

    @SpyBean
    JobOfferFacade jobOfferFacade;
    @Autowired
    CacheManager cacheManager;

    @Container
    private static final GenericContainer<?> REDIS;


    static{
        REDIS = new GenericContainer<>("redis").withExposedPorts(6379);
        REDIS.start();
    }

    @DynamicPropertySource
    public static void propertyOvveride(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
        registry.add("spring.redis.port", () -> REDIS.getFirstMappedPort().toString());
        registry.add("spring.cache.type", () -> "redis");
        registry.add("spring.cache.redis.time-to-live", () -> "PT1S");
    }


    @Test
    public void should_save_offers_to_cache_and_then_cache_is_empty_after_time_to_live_expire() throws Exception {
        //given
        ResultActions registerUserPerform = mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "userName": "user",
                        "password": "password"
                        }
                        """.trim()));
        MvcResult registerUserResult = registerUserPerform.andExpect(status().isCreated()).andReturn();
        String registerUserAsString = registerUserResult.getResponse().getContentAsString();
        RegistrationResultDto registrationResultDto = objectMapper.readValue(registerUserAsString, RegistrationResultDto.class);

        ResultActions authenticatedUser = mockMvc.perform(post("/token")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "userName": "user",
                        "password": "password"
                        }
                        """.trim()));
        MvcResult authenticatedUserResult = authenticatedUser.andExpect(status().isOk()).andReturn();
        String authenticatedUserAsString = authenticatedUserResult.getResponse().getContentAsString();
        JwtTokenResponseDto jwtTokenResponseDto = objectMapper.readValue(authenticatedUserAsString, JwtTokenResponseDto.class);
        String token = jwtTokenResponseDto.token();

        //when
        mockMvc.perform(get("/offers")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON));
        verify(jobOfferFacade, times(1)).findAllJobsOffers();
        assertThat(cacheManager.getCacheNames().contains("jobOffers")).isTrue();

        //then
        await()
                .atMost(Duration.ofSeconds(4))
                .pollInterval(Duration.ofSeconds(1))
                .untilAsserted(() -> {
                            mockMvc.perform(get("/offers")
                                    .header("Authorization", "Bearer " + token)
                                    .contentType(MediaType.APPLICATION_JSON));
                            verify(jobOfferFacade, atLeast(2)).findAllJobsOffers();
                        }
                );

    }

}
