package com.example.todo.Service;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.DTO.ToDoDTOMap;
import com.example.todo.Entity.ToDo;
import com.example.todo.Repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoService {


    private final ToDoRepository toDoRepository;
    private final ToDoDTOMap toDoDTOMap;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, ToDoDTOMap toDoDTOMap) {
        this.toDoRepository = toDoRepository;
        this.toDoDTOMap = toDoDTOMap;
    }

    public List<ToDoDTO> getToDos() {
        return toDoRepository.findAll()
                .stream().map(toDoDTOMap).collect(Collectors.toList());
    }

    public ToDo findToDo(Long id) {
        return toDoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Could not find toDo with id: " + id)
        );
    }

    public ToDoDTO getToDo(Long id) {
        return toDoRepository.findById(id)
                .map(toDoDTOMap).
                orElseThrow(
                () -> new RuntimeException("Could not find toDo with id: " + id)
        );
    }

    public void deleteToDo(Long id) {
        ToDo toDo = this.findToDo(id);
        toDoRepository.delete(toDo);
    }

    public void updateToDo(Long id, ToDo toDo) {
        ToDo userToDo = this.findToDo(id);

        if (toDo.getTitle() != null)
            userToDo.setTitle(toDo.getTitle());
        if (toDo.getDescription() != null)
            userToDo.setDescription(toDo.getDescription());

        toDoRepository.save(userToDo);
    }
}
