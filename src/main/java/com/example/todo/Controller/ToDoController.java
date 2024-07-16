package com.example.todo.Controller;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {


    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDoDTO> getToDos() {
        return toDoService.getToDos();
    }

    @GetMapping("{id}")
    public ToDoDTO getToDo(@PathVariable Long id) {
        return toDoService.getToDo(id);
    }

    @DeleteMapping("{id}")
    public void deleteToDo(@PathVariable Long id){
        toDoService.deleteToDo(id);
    }



    @PutMapping("{id}")
    public void updateToDo(@PathVariable Long id,
                           @RequestBody ToDoDTO toDo){
        toDoService.updateToDo(id, toDo);
    }

}
