package com.taskList.task.services;

import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.exceptions.ObjectNotExistException;
import com.taskList.task.models.ListTask;

import java.util.Optional;

public interface ListTaskService {

    public Optional<ListTask> getTaskListById(String id) throws ObjectNotExistException;
    public Integer addListTask(ListTask listTask) throws InsertionFailedException;

}
