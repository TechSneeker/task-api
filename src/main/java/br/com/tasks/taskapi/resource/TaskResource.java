package br.com.tasks.taskapi.resource;

import br.com.tasks.taskapi.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskResource {

    Task save(Task task);

    Task update(Task task);

    void delete(UUID id);

    List<Task> getAll();

    Task getById(UUID id);
}
