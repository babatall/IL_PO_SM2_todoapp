package com.unchk.todoapp.service;

import com.unchk.todoapp.dto.TaskDTO;
import java.util.List;

/**
 * Contrat du service métier de gestion des tâches.
 *
 * SOLID :
 *  - OCP : Ouvert à l'extension via de nouvelles implémentations.
 *  - LSP : Toute implémentation remplace cette interface sans effet de bord.
 *  - ISP : Interface dédiée aux seules opérations sur les tâches.
 *  - DIP : Le contrôleur dépend de cette abstraction, jamais de l'implémentation.
 */
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