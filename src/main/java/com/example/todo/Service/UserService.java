package com.example.todo.Service;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.DTO.UserDTO;
import com.example.todo.DTO.UserDTOMap;
import com.example.todo.Entity.ToDo;
import com.example.todo.Entity.User;
import com.example.todo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMap userDTOMap;

    @Autowired
    public UserService(UserRepository userRepository, UserDTOMap userDTOMap) {
        this.userRepository = userRepository;
        this.userDTOMap = userDTOMap;
    }

    public void addToDo(Long userId, ToDoDTO toDo) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User whit id "+ userId + " not found")
        );
        ToDo toDoToAdd = new ToDo(toDo.title(),
                                  toDo.description(),
                                  toDo.startDate(),
                                  toDo.expirationDate(),
                                  toDo.completed()
        );
        toDoToAdd.setUser(user);
        user.addToDo(toDoToAdd);
        userRepository.save(user);
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll()
                .stream().map(userDTOMap).collect(Collectors.toList());
    }

    public UserDTO getUser(Long userId) {
        return userRepository.findById(userId).
                map(userDTOMap).orElseThrow(
                () -> new RuntimeException("User with id " + userId + " not found")
        );
    }

    public void createUser(User user) {
        userRepository.save(user);
    }



    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User with id " + userId + " not found")
        );
        userRepository.delete(user);
    }

}
