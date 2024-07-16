package com.example.todo.Controller;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.DTO.UserDTO;
import com.example.todo.Entity.User;
import com.example.todo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{userId}")
    public UserDTO getUser(
            @PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public void createUser(
            @RequestBody User user
    ){
        userService.createUser(user);
    }

    //TODO: add todo to user function
    @PostMapping("/todo")
    public void addToDoToUser(@RequestParam Long userId,
                              @RequestBody ToDoDTO toDo){
        userService.addToDo(userId, toDo);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ){
        userService.deleteUser(userId);
    }

}
