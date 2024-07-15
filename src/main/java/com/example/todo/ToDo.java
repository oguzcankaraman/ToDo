package com.example.todo;


import jakarta.persistence.*;

@Entity
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


    public ToDo(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public ToDo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
