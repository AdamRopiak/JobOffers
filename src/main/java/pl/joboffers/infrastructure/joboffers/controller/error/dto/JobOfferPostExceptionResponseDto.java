package pl.joboffers.infrastructure.joboffers.controller.error.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record JobOfferPostExceptionResponseDto(List<String> messages, HttpStatus status) {
}
