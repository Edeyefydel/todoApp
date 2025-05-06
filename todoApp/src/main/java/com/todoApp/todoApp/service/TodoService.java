package com.todoApp.todoApp.service;

import com.todoApp.todoApp.models.Todo;
import com.todoApp.todoApp.models.User;
import com.todoApp.todoApp.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private UserService userService;

    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todo, String username){

        User user = userService.findByUsername(username).orElseThrow();
        todo.setUser(user);

        return todoRepository.save(todo);
    }

    public List<Todo> getTodos(String username){

        User user = userService.findByUsername(username).orElseThrow();

        return todoRepository.findByUser(user);
    }

    public Todo updateTodo(Todo todo, String username){

        User user = userService.findByUsername(username).orElseThrow();

        Todo existingTodo = todoRepository.findById(todo.getId()).orElseThrow();

        if(existingTodo.getUser().equals(user)) {
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setCompleted(todo.isCompleted());

            return todoRepository.save(existingTodo);
        } else{
            throw new RuntimeException("you are not allowed to update this todo");
        }
    }

    public void deleteTodo(Long id, String username){

        User user = userService.findByUsername(username).orElseThrow();
        Todo todo = todoRepository.findById(id).orElseThrow();
        if (todo.getUser().equals(user)) {
            todoRepository.delete(todo);
        } else {
            throw new RuntimeException("You are not Authorized to delete this todo");
        }
    }
}
