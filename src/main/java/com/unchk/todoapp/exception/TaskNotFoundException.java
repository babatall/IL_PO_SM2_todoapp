package com.unchk.todoapp.exception;


public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Tâche introuvable avec l'identifiant : " + id);
    }
}