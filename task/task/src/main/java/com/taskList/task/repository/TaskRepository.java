package com.taskList.task.repository;

import com.taskList.task.models.ListTask;
import com.taskList.task.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByListTask(ListTask listTask);

        List<Task> findByListTaskListTaskId(int listTaskId);

}
