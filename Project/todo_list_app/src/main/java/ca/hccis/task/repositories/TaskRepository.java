package ca.hccis.task.repositories;

import ca.hccis.task.jpa.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
}
