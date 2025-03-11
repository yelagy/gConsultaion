package com.example.gconsultaion.web;

import com.example.gconsultaion.entity.Patient;
import com.example.gconsultaion.service.ConsultationService;
import com.example.gconsultaion.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ConsultationService consultationService;

    @GetMapping
    public String listPatients(Model model) {
        model.addAttribute("title", "Gestion des Patients");
        model.addAttribute("content", "patients/list");
        model.addAttribute("patients", patientService.getAllPatients());
        return "template";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("title", "Ajouter un Patient");
        model.addAttribute("content", "patients/add");
        return "template";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("successMessage", "Le patient a été ajouté avec succès !");
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id)));
        model.addAttribute("title", "Modifier un Patient");
        model.addAttribute("content", "patients/edit");
        return "template";
    }

    @PostMapping("/edit/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {
        patient.setId(id);
        patientService.savePatient(patient);
        redirectAttributes.addFlashAttribute("successMessage", "Le patient a été modifié avec succès !");
        return "redirect:/patients";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (!consultationService.getConsultationsByPatientId(id).isEmpty()) {
            redirectAttributes.addFlashAttribute("warningMessage", "Ce patient a encore des consultations !");
            return "redirect:/patients";
        }

        patientService.deletePatient(id);
        redirectAttributes.addFlashAttribute("successMessage", "Le patient a été supprimé avec succès !");
        return "redirect:/patients";
    }


}
