package br.com.tasks.taskapi.resource;

import br.com.tasks.taskapi.entity.Task;
import br.com.tasks.taskapi.exception.CustomException;

import java.util.List;
import java.util.UUID;

public interface TaskResource {

    Task save(Task task);

    Task update(UUID id, Task task) throws CustomException;

    void delete(UUID id) throws CustomException;

    Task getById(UUID id) throws CustomException;

    List<Task> getAll() throws CustomException;

    List<Task> getByStatus(String status) throws CustomException;

    List<Task> getByCategory(String category) throws CustomException;

    List<Task> getByPriority(String priority) throws CustomException;

    void assign(UUID taskId, UUID userId) throws CustomException;

    void unassign(UUID taskId, UUID userId) throws CustomException;
}
