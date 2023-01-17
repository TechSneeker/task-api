package br.com.tasks.taskapi.service;

import br.com.tasks.taskapi.entity.Task;
import br.com.tasks.taskapi.exception.CustomException;
import br.com.tasks.taskapi.repository.TaskRepository;
import br.com.tasks.taskapi.resource.TaskResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService implements TaskResource {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private EntityManager entityManager;

    ModelMapper mapper = new ModelMapper();

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Task save(Task json) {
        return taskRepository.save(json);
    }

    @Override
    public Task update(UUID id, Task json) throws CustomException {
        Task taskFound = taskRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND,
                        "This task wasn't found in the database :("));

        BeanUtils.copyProperties(json, taskFound, Utils.getNullAttributes(json));

        taskRepository.save(taskFound);

        return taskFound;
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
