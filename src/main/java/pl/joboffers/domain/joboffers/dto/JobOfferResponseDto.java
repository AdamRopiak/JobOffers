package pl.joboffers.domain.joboffers.dto;

import lombok.Builder;

@Builder
public record JobOfferResponseDto(String title, String company, String salary, String offerUrl) {
}
