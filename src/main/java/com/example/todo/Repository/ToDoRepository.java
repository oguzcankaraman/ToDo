package com.example.todo.Repository;

import com.example.todo.Entity.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    @Query("SELECT todo from ToDo todo WHERE CURRENT_TIMESTAMP < todo.expirationDate AND "+
            "todo.completed = false ")
    Page<ToDo> findAllNonExpired(Pageable pageable);

    @Query("SELECT todo from ToDo todo WHERE CURRENT_TIMESTAMP < todo.expirationDate AND " +
            "todo.user.id = :userId AND todo.completed = false ")
    Page<ToDo> findAllNonExpiredByUserId(@Param("userId")Long userId, Pageable pageable);
}
