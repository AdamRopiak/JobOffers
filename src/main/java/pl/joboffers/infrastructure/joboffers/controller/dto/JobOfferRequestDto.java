package pl.joboffers.infrastructure.joboffers.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record JobOfferRequestDto(
        @NotBlank(message = "{offerurl.notblank}")
        String offerUrl,
        @NotBlank(message = "{title.notblank}")
        String title,
        @NotBlank(message = "{company.notblank}")
        String company,
        String salary) {
}
