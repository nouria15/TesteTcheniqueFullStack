package com.taskList.task.services.serviceImpl;
import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.exceptions.ObjectNotExistException;
import com.taskList.task.models.ListTask;
import com.taskList.task.models.Task;
import com.taskList.task.repository.ListTaskRepository;
import com.taskList.task.repository.TaskRepository;
import com.taskList.task.services.ListTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListTaskImpl implements ListTaskService {
    @Autowired
    ListTaskRepository listTaskRepository;
    @Autowired
   TaskServiceImpl taskServiceImpl;
    @Autowired
    TaskRepository taskRepository;
    @Override
    public Optional<ListTask> getTaskListById(String id)  throws ObjectNotExistException {
        Optional<ListTask> listTask= this.listTaskRepository.findById(id);
        if (listTask.get() == null) throw  new ObjectNotExistException("Cette liste n'existe pas ");
        listTask.get().setTasks(this.taskServiceImpl.getAllTaskByIdListTask(listTask.get().getListTaskId()));
        return listTask;
    }

    @Override
    public Integer addListTask(ListTask listTask) throws InsertionFailedException {
        ListTask listTaskTmp = this.listTaskRepository.save(listTask);
        if (listTaskTmp == null ) throw new InsertionFailedException("Cette liste n'a pas pu être créée");
        List<Task> listTasks = listTask.getTasks();
        for (Task task : listTasks) {
            task.setListTask(listTaskTmp);
            this.taskServiceImpl.addTask(task);
        }
        return listTaskTmp.getListTaskId();
    }




}
