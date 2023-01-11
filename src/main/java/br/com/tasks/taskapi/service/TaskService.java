package br.com.tasks.taskapi.service;

import br.com.tasks.taskapi.entity.Task;
import br.com.tasks.taskapi.exception.CustomException;
import br.com.tasks.taskapi.repository.TaskRepository;
import br.com.tasks.taskapi.resource.TaskResource;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskService implements TaskResource {

    TaskRepository taskRepository;

    @Override
    public Task save(Task json) {
        return taskRepository.save(json);
    }

    @Override
    public Task update(UUID id, Task json) throws CustomException {
        taskRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND,
                        "This task wasn't found in the database :("));

        Task taskUpdated = Task.builder()
                .id(id)
                .title(json.getTitle())
                .description(json.getDescription())
                .status(json.getStatus())
                .category(json.getCategory())
                .priority(json.getPriority())
                .responsible(json.getResponsible())
                .build();

        taskRepository.save(taskUpdated);

        return taskUpdated;
    }

    @Override
    public void delete(UUID id) throws CustomException {
        taskRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND,
                        "This task wasn't found in the database :("));

        taskRepository.deleteById(id);

        Optional<Task> taskExists = taskRepository.findById(id);

        if (taskExists.isPresent())
            throw new CustomException(HttpStatus.CONFLICT,
                    "Unexpectedly the task wasn't deleted :(");
    }

    @Override
    public List<Task> getAll() throws CustomException {
        List<Task> result = taskRepository.findAll();

        if (result.isEmpty())
            throw new CustomException(HttpStatus.NO_CONTENT, "No results for tasks :(");

        return result;
    }

    @Override
    public Task getById(UUID id) throws CustomException {
        return taskRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND,
                        "This task wasn't found in the database :("));
    }
}
