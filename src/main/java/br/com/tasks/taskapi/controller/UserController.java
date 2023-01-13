package br.com.tasks.taskapi.controller;

import br.com.tasks.taskapi.entity.User;
import br.com.tasks.taskapi.exception.CustomException;
import br.com.tasks.taskapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() throws CustomException {
        var test = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(test);
    }

    @GetMapping(value = "/filter/{id}")
    public ResponseEntity<User> getById(@RequestParam("id") UUID id) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
    }

    @GetMapping(value = "/filter/{name}")
    public ResponseEntity<User> getByName(@RequestParam("name") String name) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User json) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(json));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable("id") UUID id, @RequestBody User json) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, json));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") UUID id) throws CustomException {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User removed successfully :)");
    }
}
