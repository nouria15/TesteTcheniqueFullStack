package com.taskList.task.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    @Column(name = "task_name")
    private String nameTask;
    @ManyToOne
    @JoinColumn(name = "list_id")
    @JsonIgnore
    private ListTask listTask;

    public Task() {
    }
}
