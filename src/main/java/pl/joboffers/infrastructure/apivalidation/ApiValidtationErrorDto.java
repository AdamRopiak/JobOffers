package pl.joboffers.infrastructure.apivalidation;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
public record ApiValidtationErrorDto(
        List<String> messages,
        HttpStatus status
) {
}
