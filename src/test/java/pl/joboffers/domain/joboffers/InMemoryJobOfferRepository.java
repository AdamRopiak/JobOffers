package pl.joboffers.domain.joboffers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryJobOfferRepository implements JobOfferRepository{
    Map<String, JobOffer> databaseByUrl = new ConcurrentHashMap<>();
    Map<String, JobOffer> databaseById = new ConcurrentHashMap<>();

    @Override
    public List<JobOffer> findAllJobOffers() {
        return databaseByUrl.values().stream().toList();
    }

    @Override
    public JobOffer save(JobOffer newJobOffer) {
        UUID id = UUID.randomUUID();
        JobOffer jobOffer = new JobOffer(
                id.toString(),
                newJobOffer.url(),
                newJobOffer.jobName(),
                newJobOffer.company(),
                newJobOffer.salary()
        );
        databaseByUrl.put(jobOffer.url(), newJobOffer);
        databaseById.put(jobOffer.jobId(), newJobOffer);
        return jobOffer;
    }

    @Override
    public Optional<JobOffer> findJobOfferById(String jobId) {
        return Optional.ofNullable(databaseById.get(jobId));
    }

    @Override
    public boolean existJobOfferByUrl(String url) {
        return databaseByUrl.containsKey(url);
    }


}
