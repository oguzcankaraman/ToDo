package com.example.todo.Service;

import com.example.todo.DTO.ToDoDTO;
import com.example.todo.DTO.ToDoDTOMap;
import com.example.todo.Entity.ToDo;
import com.example.todo.Repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class ToDoService {


    private final ToDoRepository toDoRepository;
    private final ToDoDTOMap toDoDTOMap;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, ToDoDTOMap toDoDTOMap) {
        this.toDoRepository = toDoRepository;
        this.toDoDTOMap = toDoDTOMap;
    }

    public List<ToDoDTO> getToDos(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<ToDo> toDos = toDoRepository.findAllNonExpired(pageable);
        return toDos.getContent().stream().map(toDoDTOMap).toList();
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

    public void updateToDo(Long id, ToDoDTO toDo) {
        ToDo userToDo = this.findToDo(id);

        userToDo.setTitle(toDo.title());
        userToDo.setDescription(toDo.description());
        userToDo.setStartDate(toDo.startDate());
        userToDo.setExpirationDate(toDo.expirationDate());

        toDoRepository.save(userToDo);
    }

    public List<ToDoDTO> getToDosByUser(Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<ToDo> toDos = toDoRepository.findAllNonExpiredByUserId(userId, pageable);
        return toDos.getContent().stream().map(toDoDTOMap).toList();
    }

    public void updateToDoPartially(Long id, Map<String, Object> toDo) {
        ToDo userToDo = this.findToDo(id);

        toDo.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(userToDo.getClass(), key);

            if (field != null) {
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, userToDo, value);

                toDoRepository.save(userToDo);
            }
        });
    }
}
