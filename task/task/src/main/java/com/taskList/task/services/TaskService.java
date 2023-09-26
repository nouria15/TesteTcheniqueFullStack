package com.taskList.task.services;

import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.exceptions.ObjectNotExistException;

import com.taskList.task.models.Task;

import java.util.List;

public interface TaskService {
    public List<Task> getAllTaskByIdListTask(int listTaskId) ;
    public void addTask(Task task) throws InsertionFailedException;
}
