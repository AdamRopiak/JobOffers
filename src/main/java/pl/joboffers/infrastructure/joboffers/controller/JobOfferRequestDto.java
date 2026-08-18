package pl.joboffers.infrastructure.joboffers.controller;

import lombok.Builder;

@Builder
public record JobOfferRequestDto(String offerUrl, String title, String company, String salary) {
}
