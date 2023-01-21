package br.com.tasks.taskapi.repository;

import br.com.tasks.taskapi.entity.Task;
import br.com.tasks.taskapi.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByStatus(Status status);
}
