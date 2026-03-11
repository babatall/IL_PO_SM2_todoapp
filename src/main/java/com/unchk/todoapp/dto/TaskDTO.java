package com.unchk.todoapp.dto;

import com.unchk.todoapp.enums.TaskStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {

    private Long id;
    private String titre;
    private String description;
    private TaskStatus statut;
}