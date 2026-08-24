package pl.joboffers.infrastructure.joboffers.controller.dto;

import lombok.Builder;

@Builder
public record JobOfferRequestDto(String offerUrl, String title, String company, String salary) {
}
