package com.taskList.task.services.serviceImpl;

import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.models.Task;
import com.taskList.task.repository.TaskRepository;
import com.taskList.task.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Override
    public List<Task> getAllTaskByIdListTask(int listTaskId) {
        return this.taskRepository.findByListTaskListTaskId(listTaskId);
    }

    @Override
    public void addTask(Task task) throws  InsertionFailedException {
        Task taskTmp = this.taskRepository.save(task);
        if (taskTmp == null ) throw new InsertionFailedException("Cette tâche n'a pas pu être créée");

    }
}
