package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/")
    public String mostrarPagina(Model model) {
        // Datos que se enviarán a la vista
        model.addAttribute("curso", "Sistemas Distribuidos");
        model.addAttribute("integrantes", new String[][] {
            {"Year", "8a", "21200666", "arnol.och8a@unmsm.edu.pe", "999999999"},
            {"Keso", "Owow", "21200777", "ivan.zulca@unmsm.edu.pe", "789465312"},
            {"Xamudyo", "Gallowo", "21200999", "machelo.rico@unmsm.edu.pe", "468412377"}
        });

        return "pagina";  // El nombre de la vista que se cargará
    }
}
