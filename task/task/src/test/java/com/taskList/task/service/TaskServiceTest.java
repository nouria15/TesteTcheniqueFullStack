package com.taskList.task.service;
import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.models.Task;
import com.taskList.task.repository.TaskRepository;
import com.taskList.task.services.serviceImpl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepositoryMock;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTask() throws InsertionFailedException {
        Task task = new Task();
        task.setNameTask("Test Task");
        when(taskRepositoryMock.save(task)).thenReturn(task);
        taskService.addTask(task);
        verify(taskRepositoryMock).save(task);
    }


}
