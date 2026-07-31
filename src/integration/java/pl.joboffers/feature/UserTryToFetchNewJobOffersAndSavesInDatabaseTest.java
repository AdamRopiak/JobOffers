package pl.joboffers.feature;

import org.junit.jupiter.api.Test;
import pl.joboffers.BaseIntegrationTest;

public class UserTryToFetchNewJobOffersAndSavesInDatabaseTest extends BaseIntegrationTest {

    @Test
    public void user_should_get_job_offers_from_external_server_and_add_new_to_local_databse_but_should_be_logged_and_external_server_should_have_offers() {

   /*
    step 1: scheduler ran 1st time and made GET to external server
    step 2: user made GET /offers with no jwt token and system returned UNAUTHORIZED(401)
    step 3: user tried to get JWT token by requesting POST /token with username=User, password=Password and system returned UNAUTHORIZED(401)
    step 4: user made POST /register with username=User, password=Password and system registered user with status CREATED(201)
    step 5: user tried to get JWT token by requesting POST /token with username=User, password=Password and system returned OK(200) and jwttoken=AAAA.BBBB.CCC
    step 6: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200)
    step 7: there are 2 new offers in external HTTP server
    step 8: scheduler ran 2nd time and made GET to external server and system added 2 new offers with ids: 1 and 2 to database
    step 9: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 2 offers with ids: 1 and 2
    step 10: user made GET /offers/9999 with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned NOT_FOUND(404) with message “Offer with id 9999 not found”
    step 11: user made GET /offers/1 with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with offer
    step 12: user tried to POST /offers with no jwt token and system returned UNAUTHORIZED(401)
    step 13: user made POST /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and body=someOfferRequestDto and system returned CREATED(201) with saved offer id 3
    step 14: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 3 offers with ids: 1, 2 and 3
    step 15: scheduler ran 3rd time and made GET to external server and system added 2 new offers with ids: 4 and 5 to database
    step 16: user made GET /offers with header “Authorization: Bearer AAAA.BBBB.CCC” and system returned OK(200) with 5 offers with ids: 1, 2, 3, 4 and 5*/

    /* ---- EXTRA STEP WITH CACHE ----
    step 17: scheduler ran within 60 minutes cache TTL interval and system retrieved offers from cache without calling external HTTP server
    step 18: 60 minutes passed, cache expired, and there are 2 new offers in external HTTP server
     */

    }
}