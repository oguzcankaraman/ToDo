package com.example.todo.DTO;

import com.example.todo.Entity.ToDo;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ToDoDTOMap implements Function<ToDo, ToDoDTO> {
    @Override
    public ToDoDTO apply(ToDo toDo) {
        return new ToDoDTO( toDo.getTitle(),
                            toDo.getDescription(),
                            toDo.getStartDate(),
                            toDo.getExpirationDate(),
                            toDo.getCompleted()
        );
    }
}
