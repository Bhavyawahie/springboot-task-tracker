package com.kmh.taskmanager.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kmh.taskmanager.entities.NoteEntity;
import com.kmh.taskmanager.entities.TaskEntity;

@Service
public class NoteService {
    private TaskService taskService;
    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }
    private HashMap<Integer, TaskNotesHolder> taskNotesHolder = new HashMap<>();
    class TaskNotesHolder {
        protected Integer noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }
    public List<NoteEntity> getTaskNotes(Integer taskId) {
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null) {
            return null;
        }
        if(taskNotesHolder.get(taskId) == null) {
            taskNotesHolder.put(taskId, new TaskNotesHolder());
        }
        return taskNotesHolder.get(taskId).notes;
    }
    public NoteEntity addTaskNote(Integer taskId, String title, String body) {
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null) {
            return null;
        }
        if(taskNotesHolder.get(taskId) == null) {
            taskNotesHolder.put(taskId, new TaskNotesHolder());
        }
        TaskNotesHolder holder = taskNotesHolder.get(taskId);
        NoteEntity note = new NoteEntity();
        note.setId(holder.noteId);
        note.setTitle(title);
        note.setBody(body);
        holder.notes.add(note);
        holder.noteId++;
        return note;
    }
}