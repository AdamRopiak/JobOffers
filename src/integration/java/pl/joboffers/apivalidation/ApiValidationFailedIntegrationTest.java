package pl.joboffers.apivalidation;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import pl.joboffers.BaseIntegrationTest;
import pl.joboffers.infrastructure.apivalidation.ApiValidtationErrorDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApiValidationFailedIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_return_400_and_validation_message_when_request_url_is_empty() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "title": "Junior DevOps Engineer",
                                "company": "CDQ Poland",
                                "salary": "8k - 14k PLN",
                                "offerUrl": ""
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiValidtationErrorDto result = objectMapper.readValue(contentAsString, ApiValidtationErrorDto.class);
        assertThat(result.messages()).contains("Offer url can't be blank");


    }
    @Test
    public void should_return_400_and_validation_message_when_request_does_not_have_offer_url() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "title": "Junior DevOps Engineer",
                                "company": "CDQ Poland",
                                "salary": "8k - 14k PLN"
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiValidtationErrorDto result = objectMapper.readValue(contentAsString, ApiValidtationErrorDto.class);
        assertThat(result.messages()).contains("Offer url can't be blank");

    }
    @Test
    public void should_return_400_and_validation_message_when_request_title_is_empty() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "title": "",
                                "company": "CDQ Poland",
                                "salary": "8k - 14k PLN",
                                "offerUrl": "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd"
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiValidtationErrorDto result = objectMapper.readValue(contentAsString, ApiValidtationErrorDto.class);
        assertThat(result.messages()).contains("Job title can't be blank");
    }

    @Test
    public void should_return_400_and_validation_message_when_request_does_not_have_title() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "company": "CDQ Poland",
                                "salary": "8k - 14k PLN",
                                "offerUrl": "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd"
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiValidtationErrorDto result = objectMapper.readValue(contentAsString, ApiValidtationErrorDto.class);
        assertThat(result.messages()).contains("Job title can't be blank");
    }

    @Test
    public void should_return_400_and_validation_message_when_request_company_is_empty() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "title": "Junior DevOps Engineer",
                                "company": "",
                                "salary": "8k - 14k PLN",
                                "offerUrl": "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd"
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiValidtationErrorDto result = objectMapper.readValue(contentAsString, ApiValidtationErrorDto.class);
        assertThat(result.messages()).contains("Company name can't be blank");

    }

    @Test
    public void should_return_400_and_validation_message_when_request_does_not_have_company() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "title": "Junior DevOps Engineer",
                                "salary": "8k - 14k PLN",
                                "offerUrl": "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd"
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiValidtationErrorDto result = objectMapper.readValue(contentAsString, ApiValidtationErrorDto.class);
        assertThat(result.messages()).contains("Company name can't be blank");

    }

    @Test
    public void should_return_400_and_validation_messages_when_offerurl_title_company_are_empty() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "title": "",
                                "company": "",
                                "salary": "8k - 14k PLN",
                                "offerUrl": ""
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiValidtationErrorDto result = objectMapper.readValue(contentAsString, ApiValidtationErrorDto.class);
        assertThat(result.messages()).
                containsExactlyInAnyOrder("Offer url can't be blank",
                "Job title can't be blank",
                "Company name can't be blank");
    }
    @Test
    public void should_return_400_and_validation_messages_when_reqquest_does_not_have_offerurl_title_company() throws Exception {
        //given
        //when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "salary": "8k - 14k PLN"
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON));
        //then
        MvcResult mvcResult = perform.andExpect(status().isBadRequest()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApiValidtationErrorDto result = objectMapper.readValue(contentAsString, ApiValidtationErrorDto.class);
        assertThat(result.messages()).
                containsExactlyInAnyOrder("Offer url can't be blank",
                        "Job title can't be blank",
                        "Company name can't be blank");
    }

}
