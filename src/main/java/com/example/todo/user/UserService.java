package com.example.todo.user;

import com.example.todo.todo.ToDo;
import com.example.todo.todo.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id " + userId + " not found")
        );
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUserToDos(Long userId, List<ToDo> toDos) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id " + userId + " not found")
        );
        for (ToDo toDo : toDos) {
            toDo.setUser(user);

        }
        user.setToDos(toDos);
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id " + userId + " not found")
        );
        userRepository.delete(user);
    }
}
