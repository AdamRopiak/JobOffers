package pl.joboffers.domain.joboffers;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Builder
@Document
public record JobOffer(
        @Id
        String offerId,
        @Indexed(unique=true)  String offerUrl,
        String title,
        String company,
        String salary) {
}
