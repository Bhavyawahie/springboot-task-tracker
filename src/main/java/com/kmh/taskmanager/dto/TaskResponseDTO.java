package com.kmh.taskmanager.dto;

import java.util.Date;
import java.util.List;

import com.kmh.taskmanager.entities.NoteEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Integer taskId;
    private String title;
    private String description;
    private Date deadline;
    private Boolean completed;
    private List<NoteEntity> notes;
}