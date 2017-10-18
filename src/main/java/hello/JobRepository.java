package hello;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called JobRepository
// CRUD refers Create, Read, Update, Delete

public interface JobRepository extends CrudRepository<Job, Long> {

}