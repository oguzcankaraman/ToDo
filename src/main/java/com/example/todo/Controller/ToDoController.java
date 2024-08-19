package com.example.todo.Controller;

import com.example.todo.DTO.Pagination;
import com.example.todo.DTO.ToDoDTO;
import com.example.todo.Service.ToDoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api-v1/todos")
public class ToDoController {


    private final ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public List<ToDoDTO> getToDos(@Valid Pagination pagination) {
        return toDoService.getToDos(pagination.getPageNo(), pagination.getPageSize());

    }

    @GetMapping("{id}")
    public ToDoDTO getToDo(@PathVariable Long id) {
        return toDoService.getToDo(id);
    }

    @DeleteMapping("{id}")
    public void deleteToDo(@PathVariable Long id){
        toDoService.deleteToDo(id);
    }

    @GetMapping("user/{userID}")
    public List<ToDoDTO> getToDosByUser(@PathVariable Long userID,
                                        @Valid
                                        @RequestParam(value = "pageNo", defaultValue = "0", required = false)
                                        @Min(0) @Max(Integer.MAX_VALUE) int pageNo,
                                        @Valid
                                        @RequestParam(value = "pageSize", defaultValue = "5", required = false)
                                        @Min(1) @Max(5) int pageSize) {
        return toDoService.getToDosByUser(userID, pageNo, pageSize);
    }

    @PutMapping("{id}")
    public void updateToDo(@PathVariable Long id,
                           @RequestBody ToDoDTO toDo){
        toDoService.updateToDo(id, toDo);
    }

    @PatchMapping("{id}")
    public void updateToDoPartially(@PathVariable Long id,
                                    @RequestBody Map<String, Object> toDo){
        toDoService.updateToDoPartially(id, toDo);
    }

}
