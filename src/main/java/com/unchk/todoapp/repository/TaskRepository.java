package com.unchk.todoapp.repository;

import com.unchk.todoapp.model.Task;
import com.unchk.todoapp.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

//Couche d'acces aux données via Spring Data JPA. Interface spécialisee uniquement pour Task.
// Les services dépendent de cette interface.
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByDateCreationDesc();
    long countByStatut(TaskStatus statut);
}