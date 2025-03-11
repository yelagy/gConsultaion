package com.example.gconsultaion.web;

import com.example.gconsultaion.entity.Consultation;
import com.example.gconsultaion.service.ConsultationService;
import com.example.gconsultaion.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listConsultations(Model model) {
        model.addAttribute("title", "Gestion des Consultations");
        model.addAttribute("content", "consultations/list");
        model.addAttribute("consultations", consultationService.getAllConsultations());
        return "template";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("title", "Ajouter une consultation");
        model.addAttribute("content", "consultations/add");
        model.addAttribute("consultation", new Consultation());
        model.addAttribute("patients", patientService.getAllPatients());
        return "template";
    }

    @PostMapping("/add")
    public String addConsultation(@ModelAttribute Consultation consultation, RedirectAttributes redirectAttributes) {
        consultationService.saveConsultation(consultation);
        redirectAttributes.addFlashAttribute("successMessage", "La consultation a été ajouté avec succès !");
        return "redirect:/consultations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        Consultation consultation = consultationService.getConsultationById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid consultation Id:" + id));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String formattedDate = (consultation.getDateConsultation() != null)
                ? dateFormat.format(consultation.getDateConsultation())
                : "";

        model.addAttribute("title", "Modifier une consultation");
        model.addAttribute("consultation", consultation);
        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("content", "consultations/edit");
        model.addAttribute("patients", patientService.getAllPatients());

        return "template";
    }

    @PostMapping("/edit/{id}")
    public String updateConsultation(@PathVariable Long id, @ModelAttribute Consultation consultation, RedirectAttributes redirectAttributes) {
        consultation.setId(id);
        consultationService.saveConsultation(consultation);
        redirectAttributes.addFlashAttribute("successMessage", "La consultation a été modifié avec succès !");
        return "redirect:/consultations";
    }

    @GetMapping("/delete/{id}")
    public String deleteConsultation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        consultationService.deleteConsultation(id);
        redirectAttributes.addFlashAttribute("successMessage", "La consultation a été supprimé avec succès !");
        return "redirect:/consultations";
    }
}