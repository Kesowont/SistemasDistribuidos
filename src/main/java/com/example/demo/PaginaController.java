package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/")
    public String redirectToPagina() {
        return "redirect:/pagina"; // Redirigir a la página principal
    }

    @GetMapping("/pagina")
    public String mostrarPagina(Model model) {
        // Datos por defecto de los integrantes
        List<Integrante> integrantes = Arrays.asList(
            new Integrante("Arnold Jherico", "Ochoa Quispe", "21200148", "arnold.ochoa@unmsm.edu.pe", "998 505 497"),
            new Integrante("Ivan Frank", "Sulca Mamani", "21200111", "ivan.sulca@unmsm.edu.pe", "982 256 192"),
            new Integrante("Americo Marcelo", "Zamudio Balabarca", "21200019", "americo.zamudio@unmsm.edu.oe", "991 655 590")
        );

        model.addAttribute("curso", "Sistemas Distribuidos");
        model.addAttribute("integrantes", integrantes);
        return "pagina";
    }
}
