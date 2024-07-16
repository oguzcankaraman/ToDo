package com.example.todo.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
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

    public ToDo(String title, String description) {
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
