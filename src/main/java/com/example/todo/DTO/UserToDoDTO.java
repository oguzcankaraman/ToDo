package com.example.todo.DTO;

import java.util.List;

public record UserToDoDTO(
        List<ToDoDTO> toDoDTOs
) {
}
