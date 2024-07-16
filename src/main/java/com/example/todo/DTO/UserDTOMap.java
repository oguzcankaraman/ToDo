package com.example.todo.DTO;

import com.example.todo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMap implements Function<User,UserDTO> {

    private final ToDoDTOMap toDoDTOMap;

    @Autowired
    public UserDTOMap(ToDoDTOMap toDoDTOMap) {
        this.toDoDTOMap = toDoDTOMap;
    }

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(user.getUsername(),
                           user.getToDos().stream().map(toDoDTOMap).toList());
    }
}
