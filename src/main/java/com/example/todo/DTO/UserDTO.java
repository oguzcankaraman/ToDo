package com.example.todo.DTO;



import java.util.List;

public record UserDTO(
        String userName,
        List<ToDoDTO> toDo
) {
}
