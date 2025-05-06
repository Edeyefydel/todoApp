package com.todoApp.todoApp.repositories;

import com.todoApp.todoApp.models.Todo;
import com.todoApp.todoApp.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
}
