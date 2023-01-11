package br.com.tasks.taskapi.resource;

import br.com.tasks.taskapi.entity.User;
import br.com.tasks.taskapi.exception.CustomException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserResource {

    User save(User user);

    User update(UUID id, User user) throws CustomException;

    void delete(UUID id) throws CustomException;

    List<User> getAll();

    User getById(UUID id) throws CustomException;

    User getByName(String name) throws CustomException;
}
