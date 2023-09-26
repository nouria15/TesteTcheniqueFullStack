package com.taskList.task.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Entity
@Setter
@Getter
@AllArgsConstructor
@Table(name = "List")
public class ListTask {
    @Id
    @Column(name = "list_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int listTaskId;

    @Column(name = "list_name")
    private String name ;

    @OneToMany(mappedBy = "listTask")
    private List<Task> tasks;
    public ListTask() {
    }


}
