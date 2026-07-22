package pl.joboffers.domain.joboffers;

import pl.joboffers.domain.joboffers.dto.JobOfferDto;
import pl.joboffers.domain.joboffers.dto.JobOfferRequestDto;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.UUID;

public class JobOfferMapper {

    public static JobOfferDto mapFromJobOfferToJobOfferDto(JobOffer jobOffer){
        return JobOfferDto.builder()
                .jobId(jobOffer.jobId())
                .url(jobOffer.url())
                .jobName(jobOffer.jobName())
                .company(jobOffer.company())
                .salary(jobOffer.salary())
                .build();
            }

    public  static JobOffer mapFromJobOfferRequestDtoToJobOffer(JobOfferRequestDto jobOfferRequestDto){
        return JobOffer.builder()
                .jobId(UUID.randomUUID().toString())
                .url(jobOfferRequestDto.url())
                .jobName(jobOfferRequestDto.jobName())
                .company(jobOfferRequestDto.company())
                .salary(jobOfferRequestDto.salary())
                .build();
    }

    public static JobOfferResponseDto mapFromJobOfferToJobOfferResponseDto(JobOffer jobOffer){
        return JobOfferResponseDto.builder()
                .jobId(jobOffer.jobId())
                .url(jobOffer.url())
                .jobName(jobOffer.jobName())
                .company(jobOffer.company())
                .salary(jobOffer.salary())
                .build();
    }

    public static JobOffer mapFromJobOfferResponseDtoToJobOffer(JobOfferResponseDto jobOfferResponseDto){
        return JobOffer.builder()
                .jobId(jobOfferResponseDto.jobId())
                .url(jobOfferResponseDto.url())
                .jobName(jobOfferResponseDto.jobName())
                .company(jobOfferResponseDto.company())
                .salary(jobOfferResponseDto.salary())
                .build();
    }
}
