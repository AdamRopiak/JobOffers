package pl.joboffers.domain.joboffers;

import lombok.Builder;

@Builder
public record JobOffer(String jobId, String url, String jobName, String company, double salary) {
}
