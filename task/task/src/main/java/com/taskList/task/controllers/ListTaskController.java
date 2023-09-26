package com.taskList.task.controllers;
import com.taskList.task.exceptions.ExceptionsHandler;
import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.exceptions.ObjectNotExistException;
import com.taskList.task.models.ListTask;
import com.taskList.task.services.serviceImpl.ListTaskImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/listTask")
@CrossOrigin(origins = "*")
public class ListTaskController {
    private final String className = this.getClass().getSimpleName();

    @Autowired
    ListTaskImpl listTaskServiceImpl;

    @PostMapping("")
    public ResponseEntity<Integer> addListTask(@RequestBody ListTask listtask) throws InsertionFailedException {
        return ResponseEntity.status(HttpStatus.OK).body(this.listTaskServiceImpl.addListTask(listtask));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ListTask>> getPhaseById(@PathVariable Integer id) throws ObjectNotExistException {
        Optional<ListTask> listTask = listTaskServiceImpl.getTaskListById(id.toString());
        return ResponseEntity.status(HttpStatus.OK).body(listTask);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        return ExceptionsHandler.handleException(e, className, "Une erreur est survenue",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ObjectNotExistException.class)
    public ResponseEntity<String> objectNotExistException(ObjectNotExistException e) {
        return ExceptionsHandler.handleObjectNotExistException(className, e);
    }

    @ExceptionHandler(InsertionFailedException.class)
    public ResponseEntity<String> insertionFailedException(InsertionFailedException e) {
        return ExceptionsHandler.handleInsertionFailedException(className, e);
    }
}
