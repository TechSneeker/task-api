package br.com.tasks.taskapi.service;

import br.com.tasks.taskapi.entity.Task;
import br.com.tasks.taskapi.resource.TaskResource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskService implements TaskResource {

    @Override
    public Task save(Task task) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Optional<Task> getById(UUID id) {
        return Optional.empty();
    }
}
