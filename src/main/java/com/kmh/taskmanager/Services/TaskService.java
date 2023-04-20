package com.kmh.taskmanager.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.kmh.taskmanager.entities.TaskEntity;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");
    public TaskEntity addTask(String title, String description, String deadline) throws ParseException{
        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadlineFormatter.parse(deadline));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }

    public TaskEntity getTaskById(Integer id) {
        for (TaskEntity task : tasks) {
            if(task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(Integer id, String description, String deadline, Boolean completed) throws ParseException {
        TaskEntity exisitingTask = getTaskById(id);
        if(exisitingTask == null){
            return null;
        }
        if(description != null || deadline != null || completed != null){
            exisitingTask.setDescription(description);
            exisitingTask.setDeadline(deadlineFormatter.parse(deadline));
            exisitingTask.setCompleted(completed);
        }
        return exisitingTask;
    }
}
