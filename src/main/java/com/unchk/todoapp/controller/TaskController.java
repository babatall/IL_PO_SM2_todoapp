package com.unchk.todoapp.controller;

import com.unchk.todoapp.dto.TaskDTO;
import com.unchk.todoapp.enums.TaskStatus;
import com.unchk.todoapp.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//Contrôleur MVC — Gestion des routes HTTP pour les tâches.
// Gère uniquement le routage HTTP, délègue au service. Dépend de l'interface TaskService, pas de l'implémentation
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;



    @GetMapping
    public String listeTaches(Model model) {
        model.addAttribute("tasks",           taskService.findAll());
        model.addAttribute("totalTaches",     taskService.countAll());
        model.addAttribute("tachesTerminees", taskService.countCompleted());
        model.addAttribute("tachesEnCours",   taskService.countInProgress());
        return "liste";
    }


    // CRÉATION

    @GetMapping("/nouveau")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("taskDTO",    new TaskDTO());
        model.addAttribute("statuts",    TaskStatus.values());
        model.addAttribute("modeEdition", false);
        return "formulaire";
    }

    @PostMapping("/nouveau")
    public String creerTache(@Valid @ModelAttribute("taskDTO") TaskDTO taskDTO,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirect) {

        if (result.hasErrors()) {
            model.addAttribute("statuts",    TaskStatus.values());
            model.addAttribute("modeEdition", false);
            return "formulaire";
        }

        taskService.create(taskDTO);
        redirect.addFlashAttribute("successMessage",
                "Tâche \"" + taskDTO.getTitre() + "\" créée avec succès !");
        return "redirect:/tasks";
    }

    // MODIFICATION


    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModification(@PathVariable Long id, Model model) {
        model.addAttribute("taskDTO",    taskService.findById(id));
        model.addAttribute("statuts",    TaskStatus.values());
        model.addAttribute("modeEdition", true);
        return "formulaire";
    }

    @PostMapping("/modifier/{id}")
    public String modifierTache(@PathVariable Long id,
                                @Valid @ModelAttribute("taskDTO") TaskDTO taskDTO,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirect) {

        if (result.hasErrors()) {
            model.addAttribute("statuts",    TaskStatus.values());
            model.addAttribute("modeEdition", true);
            return "formulaire";
        }

        taskService.update(id, taskDTO);
        redirect.addFlashAttribute("successMessage",
                "Tâche modifiée avec succès !");
        return "redirect:/tasks";
    }


    // SUPPRESSION

    @PostMapping("/supprimer/{id}")
    public String supprimerTache(@PathVariable Long id,
                                 RedirectAttributes redirect) {
        taskService.delete(id);
        redirect.addFlashAttribute("successMessage",
                "Tâche supprimée avec succès !");
        return "redirect:/tasks";
    }

    // MARQUER COMME TERMINÉE


    @PostMapping("/terminer/{id}")
    public String marquerCommeTerminee(@PathVariable Long id,
                                       RedirectAttributes redirect) {
        TaskDTO task = taskService.markAsCompleted(id);
        redirect.addFlashAttribute("successMessage",
                "\"" + task.getTitre() + "\" marquée comme terminée !");
        return "redirect:/tasks";
    }
}