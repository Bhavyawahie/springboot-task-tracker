package com.kmh.taskmanager.Services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.kmh.taskmanager.entities.TaskEntity;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private int taskId = 1;

    public TaskEntity addTask(String title, String description, String deadline) {
        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        // task.setDeadline(new Date(deadline));
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
}
