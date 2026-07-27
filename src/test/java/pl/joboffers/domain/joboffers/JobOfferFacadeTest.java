package pl.joboffers.domain.joboffers;

import lombok.RequiredArgsConstructor;

import org.junit.jupiter.api.Test;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;
import pl.joboffers.domain.joboffers.dto.JobOfferRequestDto;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class JobOfferFacadeTest {

    @Test
    public void should_fetch_three_job_offers_and_save_in_database_when_database_is_empty(){
        //given
        List<JobOfferResponseDto> jobOffesFromApi = List.of(
                new JobOfferResponseDto("1", "www.joboffer1.com", "Senior Developer", "Javax", 15.234D),
                new JobOfferResponseDto("2", "www.joboffer2.com", "Senior Java Developer", "Testowa firma", 150.234D),
                new JobOfferResponseDto("3", "www.joboffer3.com", "Junior Java Developer", "FBI", 234.23D)
        );
        JobOfferFacadeTestConfiguration config = new JobOfferFacadeTestConfiguration(jobOffesFromApi);
        JobOfferFacade jobOfferFacade = config.jobOfferFacadeForTests();

        List<JobOfferDto> databaseState = jobOfferFacade.findAllJobsOffers();
        assertThat(databaseState).isEmpty();

        //when
        List<JobOfferResponseDto> savedOffers = jobOfferFacade.fetchAllJobOffersAndSave();

        //then
        assertThat(savedOffers).hasSize(3);
        List<JobOfferDto> databaseContent = jobOfferFacade.findAllJobsOffers();
        assertThat(databaseContent).hasSize(3);

    }

   @Test
    public void should_fetch_three_job_offers_and_save_only_two_offers_with_unique_url_when_there_are_four_offers_in_database(){
       //given
       JobOfferFacade jobOfferFacade = new JobOfferFacadeTestConfiguration(List.of(
               new JobOfferResponseDto("1", "www.joboffer1.com", "Senior Developer", "Javax", 15.234D),
               new JobOfferResponseDto("2", "www.joboffer2.com", "Senior Java Developer", "Testowa firma", 150.234D),
               new JobOfferResponseDto("3", "www.joboffer3.com", "Junior Java Developer", "FBI", 234.23D)
                )
       ).jobOfferFacadeForTests();
       jobOfferFacade.saveNewJobOffer(new JobOfferRequestDto("www.joboffer1.com", "Senior Developer", "Javax", 15.234D));
       jobOfferFacade.saveNewJobOffer(new JobOfferRequestDto("www.joboffer423.com", "Senior Developer423", "Javax423", 15.234D));
       jobOfferFacade.saveNewJobOffer(new JobOfferRequestDto("www.joboffer123.com", "Senior Developer123", "Javax123", 15.234D));
       assertThat(jobOfferFacade.findAllJobsOffers()).hasSize(3);

       //when
       List<JobOfferResponseDto> jobOfferResponseDto = jobOfferFacade.fetchAllJobOffersAndSave();

       //then
        assertThat(jobOfferFacade.findAllJobsOffers()).hasSize(5);
        assertTrue(jobOfferResponseDto.get(0).url().equals("www.joboffer2.com"));


   }

    @Test
    public void should_add_new_job_offer_into_database(){
        //given
        JobOfferFacadeTestConfiguration config = new JobOfferFacadeTestConfiguration();
        JobOfferFacade jobOfferFacade = config.jobOfferFacadeForTests();
        JobOfferRequestDto juniorJavaDeveloper = new JobOfferRequestDto("www.joboffer1.com", "Junior Java Developer", "Firma krzak", 15.21d);

        //when
        JobOfferDto jobOfferResponse = jobOfferFacade.saveNewJobOffer(juniorJavaDeveloper);

        //then
        assertThat(jobOfferResponse.url()).isEqualTo("www.joboffer1.com");
        assertThat(jobOfferResponse.jobName()).isEqualTo("Junior Java Developer");
        int size = jobOfferFacade.findAllJobsOffers().size();
        assertThat(size).isEqualTo(1);
    }


    @Test
    public void should_return_true_when_job_offer_with_url_exists_in_database(){
        //given
        JobOfferFacadeTestConfiguration config = new JobOfferFacadeTestConfiguration();
        JobOfferFacade jobOfferFacade = config.jobOfferFacadeForTests();
        JobOfferRequestDto juniorJavaDeveloper = new JobOfferRequestDto("www.joboffer1.com", "Junior Java Developer", "Firma krzak", 15.21d);
        jobOfferFacade.saveNewJobOffer(juniorJavaDeveloper);

        //when
        boolean exists = jobOfferFacade.existsByUrl("www.joboffer1.com");

        //then
        assertTrue(exists);
    }

    @Test
    public void should_find_one_job_offer_by_id_when_one_offer_is_saved_id_database(){
        //given
        JobOfferFacadeTestConfiguration config = new JobOfferFacadeTestConfiguration();
        JobOfferFacade jobOfferFacade = config.jobOfferFacadeForTests();
        JobOfferRequestDto jobOfferRequest = new JobOfferRequestDto("www.joboffer1.com", "Junior Java Developer", "Firma krzak", 15.21d);
        JobOfferDto jobOfferResponse = jobOfferFacade.saveNewJobOffer(jobOfferRequest);

        //when
        JobOfferDto jobOfferById = jobOfferFacade.findJobOfferById(jobOfferResponse.jobId());

        //then
        assertThat(jobOfferById).isEqualTo(JobOfferDto.builder()
                        .jobId(jobOfferResponse.jobId())
                        .url("www.joboffer1.com")
                        .jobName("Junior Java Developer")
                        .company("Firma krzak")
                        .salary(15.21d)
                .build());

    }

    @Test
    public void should_find_all_jobs_offers_in_database(){
        //given
        JobOfferFacadeTestConfiguration config = new JobOfferFacadeTestConfiguration();
        JobOfferFacade jobOfferFacade = config.jobOfferFacadeForTests();
        JobOfferRequestDto juniorJavaDeveloper1 = new JobOfferRequestDto("www.joboffer1.com", "Junior Java Developer1", "Firma krzak1", 15.21d);
        JobOfferRequestDto juniorJavaDeveloper2 = new JobOfferRequestDto("www.joboffer2.com", "Junior Java Developer2", "Firma krzak2", 16.21d);
        JobOfferRequestDto juniorJavaDeveloper3 = new JobOfferRequestDto("www.joboffer3.com", "Junior Java Developer3", "Firma krzak3", 17.21d);
        jobOfferFacade.saveNewJobOffer(juniorJavaDeveloper1);
        jobOfferFacade.saveNewJobOffer(juniorJavaDeveloper2);
        jobOfferFacade.saveNewJobOffer(juniorJavaDeveloper3);

        //when
        List<JobOfferDto> allJobsOffers = jobOfferFacade.findAllJobsOffers();

        //then
        assertThat(allJobsOffers.size()).isEqualTo(3);


    }

    @Test
    public void should_throw_exception_when_job_offer_was_not_found_by_id(){
        //give
        JobOfferFacadeTestConfiguration config = new JobOfferFacadeTestConfiguration();
        JobOfferFacade jobOfferFacade = config.jobOfferFacadeForTests();
        jobOfferFacade.findAllJobsOffers();

        //when
        Throwable throwable = catchThrowable(() -> jobOfferFacade.findJobOfferById("2"));

        //then
        assertThat(throwable).isInstanceOf(JobOfferNotFoundException.class);
        assertThat(throwable.getMessage()).isEqualTo("Job offer not found");

    }
}