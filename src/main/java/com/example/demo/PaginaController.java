package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @Autowired
    private IntegranteRepository integranteRepository;

    @GetMapping("/")
    public String mostrarPagina(Model model) {
        model.addAttribute("curso", "Sistemas Distribuidos");
        model.addAttribute("integrantes", integranteRepository.findAll());
        return "pagina"; // El nombre de la vista
    }
}
