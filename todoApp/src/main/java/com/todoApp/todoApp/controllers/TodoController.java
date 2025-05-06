package com.todoApp.todoApp.controllers;

import com.todoApp.todoApp.models.Todo;
import com.todoApp.todoApp.models.User;
import com.todoApp.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo, @AuthenticationPrincipal User user){

        return todoService.createTodo(todo, user.getUsername());
    }

    @GetMapping
    public List<Todo> getTodos(@AuthenticationPrincipal User user){

        return todoService.getTodos(user.getUsername());
    }

    @PutMapping("/{id}")
    public  Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo, @AuthenticationPrincipal User user){

        return todoService.updateTodo(todo, user.getUsername());
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id, @AuthenticationPrincipal User user){

        todoService.deleteTodo(id, user.getUsername());
    }
}
