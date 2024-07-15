package com.example.todo.todo;

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
    public List<ToDo> getToDos() {
        return toDoService.getToDos();
    }

    @GetMapping("{id}")
    public ToDo getToDo(@PathVariable Long id) {
        return toDoService.getToDo(id);
    }

    @DeleteMapping("{id}")
    public void deleteToDo(@PathVariable Long id){
        toDoService.deleteToDo(id);
    }

    @PostMapping
    public ToDo addToDo(@RequestBody ToDo toDo){
        return toDoService.addToDo(toDo);
    }

    @PutMapping("{id}")
    public void updateToDo(@PathVariable Long id,
                           @RequestBody ToDo toDo){
        toDoService.updateToDo(id, toDo);
    }

}
