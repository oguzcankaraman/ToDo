package com.example.todo.DTO;

import com.example.todo.Entity.ToDo;

import java.util.List;

public record UserDTO(
        String userName,
        List<ToDo> toDo
) {
}
