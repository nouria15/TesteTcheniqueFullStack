package com.taskList.task.repository;

import com.taskList.task.models.ListTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ListTaskRepository extends JpaRepository<ListTask, String> {
}