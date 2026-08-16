package pl.joboffers.domain.joboffers;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@Document
public record JobOffer(
        @Id
        String jobId,
        @Indexed(unique=true)  String url,
        String jobName,
        String company,
        String salary) {
}
