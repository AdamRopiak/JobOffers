package pl.joboffers.domain.joboffers;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class InMemoryJobOfferRepository implements JobOfferRepository {
    Map<String, JobOffer> databaseByUrl = new ConcurrentHashMap<>();
    Map<String, JobOffer> databaseById = new ConcurrentHashMap<>();

    @Override
    public List<JobOffer> findAll() {
        return databaseById.values().stream().toList();
    }

    @Override
    public <S extends JobOffer> S save(S entity) {
        String id = (entity.jobId() != null && !entity.jobId().isEmpty())
                ? entity.jobId()
                : UUID.randomUUID().toString();
        JobOffer jobOffer = new JobOffer(
                id,
                entity.url(),
                entity.jobName(),
                entity.company(),
                entity.salary()
        );
        databaseById.put(jobOffer.jobId(), entity);
        return entity;
    }

    @Override
    public Optional<JobOffer> findById(String jobId) {
        return Optional.ofNullable(databaseById.get(jobId));
    }

    @Override
    public boolean existsJobOfferByUrl(String url) {
        return databaseById.values().stream()
                .anyMatch(entity -> entity.url().equals(url));
    }


    @Override
    public <S extends JobOffer> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends JobOffer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends JobOffer> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends JobOffer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends JobOffer> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends JobOffer> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends JobOffer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends JobOffer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends JobOffer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends JobOffer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

  /*  @Override
    public List<JobOffer> findAll() {
        return List.of();
    }*/

    @Override
    public List<JobOffer> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(JobOffer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends JobOffer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<JobOffer> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<JobOffer> findAll(Pageable pageable) {
        return null;
    }
}
