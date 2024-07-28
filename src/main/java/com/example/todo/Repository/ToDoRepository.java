package com.example.todo.Repository;

import com.example.todo.Entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query("SELECT todo from ToDo todo WHERE CURRENT_DATE < todo.expirationDate")
    List<ToDo> findAllNonExpired();

    @Query("SELECT todo from ToDo todo WHERE CURRENT_DATE < todo.expirationDate AND " +
            "todo.user.id = :userId")
    List<ToDo> findAllNonExpiredByUserId(@Param("userId")Long userId);
}
