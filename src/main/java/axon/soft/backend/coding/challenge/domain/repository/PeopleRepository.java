package axon.soft.backend.coding.challenge.domain.repository;

import axon.soft.backend.coding.challenge.domain.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PeopleRepository extends CrudRepository<PersonEntity, Integer> {
}
