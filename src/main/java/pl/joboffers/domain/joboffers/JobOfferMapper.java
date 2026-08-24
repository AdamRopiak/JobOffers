package pl.joboffers.domain.joboffers;

import pl.joboffers.domain.joboffers.dto.JobOfferDto;
import pl.joboffers.infrastructure.joboffers.controller.dto.JobOfferRequestDto;
import pl.joboffers.domain.joboffers.dto.JobOfferResponseDto;

import java.util.UUID;

public class JobOfferMapper {

    public static JobOfferDto mapFromJobOfferToJobOfferDto(JobOffer jobOffer){
        return JobOfferDto.builder()
                .offerId(jobOffer.offerId())
                .offerUrl(jobOffer.offerUrl())
                .title(jobOffer.title())
                .company(jobOffer.company())
                .salary(jobOffer.salary())
                .build();
            }

    public  static JobOffer mapFromJobOfferRequestDtoToJobOffer(JobOfferRequestDto jobOfferRequestDto){
        return JobOffer.builder()
                .offerId(UUID.randomUUID().toString())
                .offerUrl(jobOfferRequestDto.offerUrl())
                .title(jobOfferRequestDto.title())
                .company(jobOfferRequestDto.company())
                .salary(jobOfferRequestDto.salary())
                .build();
    }

    public static JobOfferResponseDto mapFromJobOfferToJobOfferResponseDto(JobOffer jobOffer){
        return JobOfferResponseDto.builder()
                .offerUrl(jobOffer.offerUrl())
                .title(jobOffer.title())
                .company(jobOffer.company())
                .salary(jobOffer.salary())
                .build();
    }

    public static JobOffer mapFromJobOfferResponseDtoToJobOffer(JobOfferResponseDto jobOfferResponseDto){
        return JobOffer.builder()
                .offerUrl(jobOfferResponseDto.offerUrl())
                .title(jobOfferResponseDto.title())
                .company(jobOfferResponseDto.company())
                .salary(jobOfferResponseDto.salary())
                .build();
    }
}
