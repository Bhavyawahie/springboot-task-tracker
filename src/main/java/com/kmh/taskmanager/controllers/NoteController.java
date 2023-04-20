package com.kmh.taskmanager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kmh.taskmanager.Services.NoteService;
import com.kmh.taskmanager.dto.CreateNoteDTO;
import com.kmh.taskmanager.dto.CreateNoteResponseDTO;
import com.kmh.taskmanager.entities.NoteEntity;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NoteController {
    private NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
        var notes = noteService.getTaskNotes(taskId);
        return ResponseEntity.ok(notes);
    }
    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") Integer taskId , @RequestBody CreateNoteDTO body) {
        var note = noteService.addTaskNote(taskId, body.getTitle(), body.getBody());
        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
    }
}
