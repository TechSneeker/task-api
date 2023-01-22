package br.com.tasks.taskapi.service;

import br.com.tasks.taskapi.entity.User;
import br.com.tasks.taskapi.exception.CustomException;
import br.com.tasks.taskapi.repository.UserRepository;
import br.com.tasks.taskapi.resource.UserResource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserResource {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User json) {
        return userRepository.save(json);
    }

    @Override
    public User update(UUID id, User json) throws CustomException {
        User userFound = userRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND,
                        "This user wasn't found in the database :("));

        BeanUtils.copyProperties(json, userFound, Utils.getNullAttributes(json));

        userRepository.save(userFound);

        return userFound;
    }

    @Override
    public void delete(UUID id) throws CustomException {
        userRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND,
                        "This user wasn't found in the database :("));

        userRepository.deleteById(id);

        Optional<User> userExists = userRepository.findById(id);

        if (userExists.isPresent())
            throw new CustomException(HttpStatus.CONFLICT,
                    "Unexpectedly the user wasn't deleted :(");
    }

    @Override
    public List<User> getAll() throws CustomException {
        List<User> result = userRepository.findAll();

        if (result.isEmpty())
            throw new CustomException(HttpStatus.NO_CONTENT,
                    "No results for users :(");

        return result;
    }

    @Override
    public User getById(UUID id) throws CustomException {
        return userRepository.findById(id).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND,
                        "This user wasn't found in the database :("));
    }

    @Override
    public User getByName(String name) throws CustomException {
        return userRepository.findByNameIgnoringCase(name).orElseThrow(() ->
                new CustomException(HttpStatus.NOT_FOUND,
                        "This user wasn't found in the database :("));
    }
}
