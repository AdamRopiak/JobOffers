package pl.joboffers.domain.joboffers.dto;

import lombok.Builder;

@Builder
public record JobOfferDto(String offerId, String offerUrl, String title, String company, String salary) {
}
