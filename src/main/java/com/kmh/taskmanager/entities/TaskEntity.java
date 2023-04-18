package com.kmh.taskmanager.entities;

import java.util.Date;

import lombok.Data;

@Data   //data mapping: helps with providing getters and setters
public class TaskEntity {
    private Integer id;
    private String title;
    private String description;
    private Date deadline;
    private Boolean completed;
}
