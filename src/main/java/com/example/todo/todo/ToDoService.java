package com.example.todo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {


    ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }

    public ToDo getToDo(Long id) {
        return toDoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Could not find toDo with id: " + id)
        );
    }

    public void deleteToDo(Long id) {
        ToDo toDo = this.getToDo(id);
        toDoRepository.delete(toDo);
    }

    public ToDo addToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public void updateToDo(Long id, ToDo toDo) {
        ToDo userToDo = this.getToDo(id);

        if (toDo.getTitle() != null)
            userToDo.setTitle(toDo.getTitle());
        if (toDo.getDescription() != null)
            userToDo.setDescription(toDo.getDescription());

        toDoRepository.save(userToDo);
    }
}
