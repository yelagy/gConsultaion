package com.example.gconsultaion.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @GetMapping
    public String showContactPage(Model model) {
        model.addAttribute("title", "Contactez-nous");
        model.addAttribute("content", "pages/contact");  // Le chemin relatif à 'templates'
        return "template";  // Affiche le template global avec le contenu dynamique
    }
}
