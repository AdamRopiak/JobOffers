package pl.joboffers.infrastructure.joboffers.controller.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record JobOfferNotFoundExceptionResponseDto(String message, HttpStatus status) {
}
