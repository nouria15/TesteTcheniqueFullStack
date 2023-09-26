package com.taskList.task.controllers;
import com.taskList.task.exceptions.ExceptionsHandler;
import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.models.Task;
import com.taskList.task.services.serviceImpl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {
    private final String className = this.getClass().getSimpleName();

    @Autowired
    TaskServiceImpl taskServiceImpl;

    @PostMapping("")
    public ResponseEntity<String> addETask(@RequestBody Task task) throws InsertionFailedException {
        this.taskServiceImpl.addTask(task);
        return ResponseEntity.status(HttpStatus.OK).body("Cette tâche ajoutée à la list");
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        return ExceptionsHandler.handleException(e, className, "Une erreur est survenue",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InsertionFailedException.class)
    public ResponseEntity<String> insertionFailedException(InsertionFailedException e) {
        return ExceptionsHandler.handleInsertionFailedException(className, e);
    }
}
