package com.unchk.todoapp.dto;

import com.unchk.todoapp.enums.TaskStatus;
import lombok.*;

//Mon dto qui permet a ne pas exposer les donnees du DB
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