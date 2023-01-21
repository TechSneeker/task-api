package br.com.tasks.taskapi.service;

import br.com.tasks.taskapi.entity.Task;
import br.com.tasks.taskapi.entity.User;
import br.com.tasks.taskapi.entity.enums.Status;
import br.com.tasks.taskapi.exception.CustomException;
import br.com.tasks.taskapi.repository.TaskRepository;
import br.com.tasks.taskapi.repository.UserRepository;
import br.com.tasks.taskapi.resource.TaskResource;

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
    UserRepository userRepository;

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
    public void assign(UUID taskId, UUID userId) throws CustomException {
        Task taskFound = taskRepository.findById(taskId).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, "This task wasn't found in the database :("));

        User userFound = userRepository.findById(userId).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, "This user wasn't found in the database :("));

        taskFound.assign(userFound);
        taskRepository.save(taskFound);
    }

    @Override
    public void unassign(UUID taskId, UUID userId) throws CustomException {
        Task taskFound = taskRepository.findById(taskId).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, "This task wasn't found in the database :("));

        User userFound = userRepository.findById(userId).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND, "This user wasn't found in the database :("));

        taskFound.unassign(userFound);
        taskRepository.save(taskFound);
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
            throw new CustomException(HttpStatus.NO_CONTENT, "No results for your search :(");

        return result;
    }

    @Override
    public List<Task> getByStatus(String status) throws CustomException {

        Status statusValue = Status.get(status);

        List<Task> result = taskRepository.findByStatus(statusValue);

        if (result.isEmpty())
            throw new CustomException(HttpStatus.NO_CONTENT, "No results for your search :(");

        return result;
    }

    @Override
    public Task getById(UUID id) throws CustomException {
        return taskRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND,
                        "This task wasn't found in the database :("));
    }

}
