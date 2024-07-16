package com.example.todo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "user_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDo> toDos;


    public User(Long id, String username, List<ToDo> toDos) {
        this.toDos = toDos;
        this.id = id;
        this.username = username;
    }

    public User(String username, List<ToDo> toDos) {
        this.toDos = toDos;
        this.username = username;
    }

    public User() {
    }

    public void addToDo(ToDo toDo) {
        toDos.add(toDo);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", username='" + username + '\'' +
                ", toDos=" + toDos +
                '}';
    }
}
