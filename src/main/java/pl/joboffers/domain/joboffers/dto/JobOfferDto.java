package pl.joboffers.domain.joboffers.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record JobOfferDto(String offerId, String offerUrl, String title, String company, String salary) implements Serializable {
}
