package com.unchk.todoapp.mapper;

import com.unchk.todoapp.dto.TaskDTO;
import com.unchk.todoapp.model.Task;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {

    /**
     * Convertit une entité Task en TaskDTO.
     */
    public TaskDTO toDTO(Task task) {
        if (task == null) return null;

        return TaskDTO.builder()
                .id(task.getId())
                .titre(task.getTitre())
                .description(task.getDescription())
                .statut(task.getStatut())
                .build();
    }

    /**
     * Convertit un TaskDTO en entité Task.
     */
    public Task toEntity(TaskDTO dto) {
        if (dto == null) return null;

        return Task.builder()
                .id(dto.getId())
                .titre(dto.getTitre())
                .description(dto.getDescription())
                .statut(dto.getStatut())
                .build();
    }
}