package com.taskList.task.controller;
import com.taskList.task.controllers.TaskController;
import com.taskList.task.exceptions.ExceptionsHandler;
import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.models.Task;
import com.taskList.task.services.serviceImpl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.nimbusds.jose.shaded.gson.GsonBuilder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    MockMvc mockMvc;

    @Mock
    TaskServiceImpl taskServiceImpl;

    @InjectMocks
    TaskController taskController;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.taskController)
                .setControllerAdvice(new ExceptionsHandler())
                .build();
    }

    @Test
    void testAddETask() throws Exception {
        Task task = new Task();
        task.setNameTask("test");
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new GsonBuilder().create().toJson(task)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(this.taskServiceImpl).addTask(taskCaptor.capture());
        assertEquals(task.getNameTask(), taskCaptor.getValue().getNameTask());
    }

    @Test
    void testAddETask_InsertionFailedException() throws Exception {
        Task task = new Task();
        doThrow(new InsertionFailedException("Insertion failed")).when(taskServiceImpl).addTask(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new GsonBuilder().create().toJson(task)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }
}
