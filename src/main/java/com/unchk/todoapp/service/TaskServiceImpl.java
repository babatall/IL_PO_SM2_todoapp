package com.unchk.todoapp.service;

import com.unchk.todoapp.dto.TaskDTO;
import com.unchk.todoapp.model.Task;
import com.unchk.todoapp.enums.TaskStatus;
import com.unchk.todoapp.exception.TaskNotFoundException;
import com.unchk.todoapp.mapper.TaskMapper;
import com.unchk.todoapp.repository.TaskRepository;
import com.unchk.todoapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper     taskMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAll() {
        log.info("Récupération de toutes les tâches");
        return taskRepository.findAllByOrderByDateCreationDesc()
                .stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO findById(Long id) {
        log.info("Recherche de la tâche id={}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toDTO(task);
    }

    @Override
    public TaskDTO create(TaskDTO taskDTO) {
        log.info("Création de la tâche : {}", taskDTO.getTitre());
        taskDTO.setStatut(TaskStatus.EN_COURS);
        Task saved = taskRepository.save(taskMapper.toEntity(taskDTO));
        return taskMapper.toDTO(saved);
    }

    @Override
    public TaskDTO update(Long id, TaskDTO taskDTO) {
        log.info("Mise à jour de la tâche id={}", id);
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        existing.setTitre(taskDTO.getTitre());
        existing.setDescription(taskDTO.getDescription());
        existing.setStatut(taskDTO.getStatut());

        return taskMapper.toDTO(taskRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression de la tâche id={}", id);
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO markAsCompleted(Long id) {
        log.info("Tâche id={} marquée comme terminée", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setStatut(TaskStatus.TERMINE);
        return taskMapper.toDTO(taskRepository.save(task));
    }

    @Override
    @Transactional(readOnly = true)
    public long countAll() {
        return taskRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long countCompleted() {
        return taskRepository.countByStatut(TaskStatus.TERMINE);
    }

    @Override
    @Transactional(readOnly = true)
    public long countInProgress() {
        return taskRepository.countByStatut(TaskStatus.EN_COURS);
    }
}