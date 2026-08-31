package pl.joboffers.controller.error;


import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import pl.joboffers.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OfferUrlDuplicateErrorHandlerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void should_retrun_409_when_added_offer_with_already_existing_offerurl() throws Exception {
        //step 1 Adding job offer with url https://nofluffjobs.com
        //given && when
        ResultActions perform = mockMvc.perform(post("/offers")
                .content(
                        """
                                {
                                "title": "Junior DevOps Engineer",
                                "company": "CDQ Poland",
                                "salary": "8k - 14k PLN",
                                "offerUrl": "https://nofluffjobs.com"
                                }        
                                """.trim()
                )
                .contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"));
        //then
        assertThat(perform.andExpect(status().isCreated()));

        //step 2 Adding second job offer with url https://nofluffjobs.com and DuplicateKeyException show up

    ResultActions performDuplicated = mockMvc.perform(post("/offers")
            .content(
                    """
                            {
                            "title": "Junior DevOps Engineer",
                            "company": "CDQ Poland",
                            "salary": "8k - 14k PLN",
                            "offerUrl": "https://nofluffjobs.com"
                            }        
                            """.trim()
            )
            .contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"));

    //then
    assertThat(performDuplicated.andExpect(status().isConflict()));
}
}
