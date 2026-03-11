package com.unchk.todoapp.enums;

import lombok.Getter;
@Getter
public enum TaskStatus {

    EN_COURS("En cours"),
    TERMINE("Terminé");

    private final String libelle;

    TaskStatus(String libelle) {
        this.libelle = libelle;
    }
}