package pl.joboffers.domain.joboffers;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
public record JobOffer(
        @Id
        String jobId,
        String url,
        String jobName,
        String company,
        String salary) {
}
