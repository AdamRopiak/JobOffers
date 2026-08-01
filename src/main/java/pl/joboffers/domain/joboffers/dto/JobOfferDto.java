package pl.joboffers.domain.joboffers.dto;

import lombok.Builder;

@Builder
public record JobOfferDto(String jobId, String url, String jobName, String company, String salary) {
}
