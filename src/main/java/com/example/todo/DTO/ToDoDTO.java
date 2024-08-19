package com.example.todo.DTO;

import java.time.LocalDate;

public record ToDoDTO(
        String title,
        String description,
        LocalDate startDate,
        LocalDate expirationDate,
        Boolean completed
) {
}
