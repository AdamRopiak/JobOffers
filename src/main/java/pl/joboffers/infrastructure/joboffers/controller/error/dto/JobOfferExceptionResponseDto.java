package pl.joboffers.infrastructure.joboffers.controller.error.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record JobOfferExceptionResponseDto(String message, HttpStatus status) {
}
