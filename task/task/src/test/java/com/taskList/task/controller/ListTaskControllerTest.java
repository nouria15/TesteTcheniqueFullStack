package com.taskList.task.controller;
import com.taskList.task.exceptions.ExceptionsHandler;
import com.taskList.task.exceptions.InsertionFailedException;
import com.taskList.task.exceptions.ObjectNotExistException;
import com.taskList.task.models.ListTask;
import com.taskList.task.services.serviceImpl.ListTaskImpl;
import com.taskList.task.controllers.ListTaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Optional;
import com.nimbusds.jose.shaded.gson.GsonBuilder;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ListTaskControllerTest {
        MockMvc mockMvc;

        @Mock
        ListTaskImpl listTaskServiceImpl;

        @InjectMocks
        ListTaskController listTaskController;

        @BeforeEach
        void setup() {
            this.mockMvc = MockMvcBuilders.standaloneSetup(this.listTaskController)
                    .setControllerAdvice(new ExceptionsHandler())
                    .build();
        }


        @Test
        void testGetPhaseById() throws Exception {
            ListTask listTask = new ListTask();
            listTask.setListTaskId(1);

            when(listTaskServiceImpl.getTaskListById("1")).thenReturn(Optional.of(listTask));

            mockMvc.perform(get("/listTask/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.listTaskId").value(1));
        }

        @Test
        void testGetPhaseById_ObjectNotExistException() throws Exception {
            when(listTaskServiceImpl.getTaskListById("1")).thenThrow(new ObjectNotExistException("Object not found"));

            mockMvc.perform(get("/listTask/1"))
                    .andExpect(status().isNotFound())
                    .andExpect(content().string("Object not found"));
        }

    @Test
    void testAddListTask_InsertionFailedException() throws Exception {
        ListTask listTask = new ListTask();

        doThrow(new InsertionFailedException("Insertion failed")).when(listTaskServiceImpl).addListTask(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/listTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new GsonBuilder().create().toJson(listTask)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

}

