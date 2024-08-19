package com.example.todo.Entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;


import java.time.LocalDate;

@Data
@Entity
@Table(name = "to_do")
public class ToDo {
    @Id
    @SequenceGenerator(
            name = "todo_sequence",
            sequenceName = "todo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "todo_sequence")

    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate expirationDate;
    private Boolean completed;
    private Boolean scheduled;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;


    public ToDo(Long id, String title, String description, User user) {
        this.user = user;
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public ToDo(String title, String description, User user) {
        this.user = user;
        this.title = title;
        this.description = description;
    }

    public ToDo(String title, String description, LocalDate startDate, LocalDate expirationDate, Boolean completed) {
        this.completed = completed;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.title = title;
        this.description = description;
    }

    public ToDo() {

    }

    @Override
    public String toString() {
        return "ToDo{" +
                ", id=" + id + '\'' +
                ", description='" + description + '\'' +
                "title='" + title +
                '}';
    }
}
