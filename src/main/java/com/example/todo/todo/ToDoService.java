package com.example.todo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {


    ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public boolean isIdNull(Long id) {
        return toDoRepository.findById(id).isEmpty();
    }

    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }


    public Optional<ToDo> getToDo(Long id) {
        if (isIdNull(id)) {
            throw new IllegalArgumentException("To-Do with id " + id + " does not exist");
        }
        return toDoRepository.findById(id);
    }

    public void deleteToDo(Long id) {
        if (isIdNull(id)) {
            throw new IllegalArgumentException("To-Do with id " + id + " does not exist");
        }
        toDoRepository.deleteById(id);
    }

    public void addToDo(ToDo toDo) {
        toDoRepository.save(toDo);
    }

    public void updateToDo(Long id, ToDo toDo) {
        if (isIdNull(id)) {
            throw new IllegalArgumentException("To-Do with id " + id + " does not exist");
        }
        ToDo var_ToDo = toDoRepository.findById(id).get();
        var_ToDo.setTitle(toDo.getTitle());
        var_ToDo.setDescription(toDo.getDescription());
        toDoRepository.save(var_ToDo);
    }
}
