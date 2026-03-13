package com.unchk.todoapp.service;

import com.unchk.todoapp.dto.TaskDTO;
import java.util.List;

// Le contrôleur depend de cette abstraction, jamais de l'implementation.
public interface TaskService {
    List<TaskDTO> findAll();
    TaskDTO findById(Long id);
    TaskDTO create(TaskDTO taskDTO);
    TaskDTO update(Long id, TaskDTO taskDTO);
    void delete(Long id);
    TaskDTO markAsCompleted(Long id);
    long countAll();
    long countCompleted();
    long countInProgress();
}