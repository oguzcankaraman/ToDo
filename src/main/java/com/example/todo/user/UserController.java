package com.example.todo.user;

import com.example.todo.todo.ToDo;
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
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{userId}")
    public User getUser(
            @PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public void createUser(
            @RequestBody User user
    ){
        userService.createUser(user);
    }

    @PutMapping("{userId}")
    public void updateUserToDos(
            @RequestBody List<ToDo> toDos,
            @PathVariable Long userId
    ){
        userService.updateUserToDos(userId, toDos);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ){
        userService.deleteUser(userId);
    }

}
