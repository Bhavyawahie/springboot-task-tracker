package com.kmh.taskmanager.controllers;

import java.text.ParseException;
import java.util.List;

import com.kmh.taskmanager.Services.NoteService;
import com.kmh.taskmanager.Services.TaskService;
import com.kmh.taskmanager.dto.CreateTaskDTO;
import com.kmh.taskmanager.dto.ErrorResponseDTO;
import com.kmh.taskmanager.dto.TaskResponseDTO;
import com.kmh.taskmanager.dto.UpdateTaskDTO;
import com.kmh.taskmanager.entities.TaskEntity;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final NoteService noteService;
    private final ModelMapper modalMapper = new ModelMapper();
    //constructor that sets taskservice via dependency injection
    public TaskController(TaskService taskService, NoteService noteService) {
        this.taskService = taskService;
        this.noteService = noteService;
    }

    @GetMapping("") 
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Integer id) {
        var task = taskService.getTaskById(id);
        var notes = noteService.getTaskNotes(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        var taskWithNotesResponse = modalMapper.map(task , TaskResponseDTO.class);
        taskWithNotesResponse.setNotes(notes);
        return ResponseEntity.ok(taskWithNotesResponse);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if(task == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e) {
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Format"));
        }
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Something went wrong, but don't fret!"));
    }
}
