package com.kmh.taskmanager.entities;

import lombok.Data;

@Data
public class NoteEntity {
    private Integer id;
    private String title;
    private String body;
}
