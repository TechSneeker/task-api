package br.com.tasks.taskapi.controller;

import br.com.tasks.taskapi.entity.Task;
import br.com.tasks.taskapi.entity.User;
import br.com.tasks.taskapi.entity.enums.Category;
import br.com.tasks.taskapi.entity.enums.Status;
import br.com.tasks.taskapi.exception.CustomException;
import br.com.tasks.taskapi.service.TaskService;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAll() throws CustomException {
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping(value = "/filter", params = "id")
    public ResponseEntity<Task> getById(@RequestParam("id") UUID id) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getById(id));
    }

    @GetMapping(value = "/filter", params = "status")
    public ResponseEntity<List<Task>> getByStatus(@RequestParam("status") String status) throws CustomException {
        List<Task> tasks = taskService.getByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping(value = "/filter", params = "category")
    public ResponseEntity<List<Task>> getByCategory(@RequestParam("category") String category) throws CustomException {
        List<Task> tasks = taskService.getByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping(value = "/filter", params = "priority")
    public ResponseEntity<List<Task>> getByPriority(@RequestParam("priority") String priority) throws CustomException {
        List<Task> tasks = taskService.getByPriority(priority);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody Task json) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(json));
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<Task> update(@PathVariable("id") UUID id, @RequestBody Task json) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(id, json));
    }

    @PatchMapping(value = "/assign/")
    public ResponseEntity<?> assign(@RequestParam("taskId") UUID taskId,
                                    @RequestParam("userId") UUID userId) throws CustomException {

        taskService.assign(taskId, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/unassign/")
    public ResponseEntity<?> unassign(@RequestParam("taskId") UUID taskId,
                                      @RequestParam("userId") UUID userId) throws CustomException {

        taskService.unassign(taskId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") UUID id) throws CustomException {
        taskService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task removed successfully :)");
    }

}
