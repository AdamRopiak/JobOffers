package pl.joboffers.domain.joboffers.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record JobOfferResponseDto (String title, String company, String salary, String offerUrl) implements Serializable {
}
