package ca.hccis.task.repositories;

import ca.hccis.task.jpa.entity.CodeValue;
import ca.hccis.task.jpa.entity.CodeValueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValueRepository extends CrudRepository<CodeValue, CodeValueId> {
}