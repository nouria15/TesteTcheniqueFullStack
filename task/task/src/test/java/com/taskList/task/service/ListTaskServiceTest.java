package com.taskList.task.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.exceptions.ObjectNotExistException;
import com.taskList.task.models.ListTask;
import com.taskList.task.models.Task;
import com.taskList.task.repository.ListTaskRepository;
import com.taskList.task.services.serviceImpl.ListTaskImpl;
import com.taskList.task.services.serviceImpl.TaskServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ListTaskServiceTest {

    @Mock
    private ListTaskRepository listTaskRepository;

    @Mock
    private TaskServiceImpl taskServiceImpl;

    @InjectMocks
    private ListTaskImpl listTaskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTaskListById() throws ObjectNotExistException {
        String id = "1";
        ListTask expectedListTask = new ListTask();
        expectedListTask.setListTaskId(Integer.parseInt(id));
        when(listTaskRepository.findById(id)).thenReturn(Optional.of(expectedListTask));
        Optional<ListTask> result = listTaskService.getTaskListById(id);
        assertTrue(result.isPresent());
        assertEquals(String.valueOf(id), String.valueOf(result.get().getListTaskId()));
        verify(listTaskRepository, times(1)).findById(id);

    }

    @Test
    public void testAddListTask() throws InsertionFailedException {
        ListTask listTask = new ListTask();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        tasks.add(new Task());
        listTask.setTasks(tasks);
        when(listTaskRepository.save(listTask)).thenReturn(listTask);
        doNothing().when(taskServiceImpl).addTask(any(Task.class));
        Integer result = listTaskService.addListTask(listTask);
        assertEquals(listTask.getListTaskId(), result);
        verify(listTaskRepository, times(1)).save(listTask);
        verify(taskServiceImpl, times(tasks.size())).addTask(any(Task.class));
    }
}








