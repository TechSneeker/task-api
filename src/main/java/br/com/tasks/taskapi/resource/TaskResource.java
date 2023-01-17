package br.com.tasks.taskapi.resource;

import br.com.tasks.taskapi.entity.Task;
import br.com.tasks.taskapi.exception.CustomException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;
import java.util.UUID;

public interface TaskResource {

    Task save(Task task);

    Task update(UUID id, Task task) throws CustomException, JsonMappingException;

    void delete(UUID id) throws CustomException;

    List<Task> getAll() throws CustomException;

    Task getById(UUID id) throws CustomException;
}
