package com.kmh.taskmanager.controllers;

import java.util.List;

import com.kmh.taskmanager.Services.TaskService;
import com.kmh.taskmanager.entities.TaskEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    //constructor that sets taskservice via dependency injection
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("") 
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

}
