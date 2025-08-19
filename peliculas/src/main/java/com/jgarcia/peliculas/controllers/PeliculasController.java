package com.jgarcia.peliculas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class PeliculasController {
    @GetMapping("/pelicula")
    public String pelicula(Model model) {
        model.addAttribute("mensaje", "Bienvenido a su sitio de películas");

        model.addAttribute("nombre", "José L. García");
        String titulo = "The Avengers";
        Date fechaResenia = new Date();
        String genero = "Action";
        int anioEstreno = 2012;
        double rating = 8.1;
        boolean vigente = true;

        model.addAttribute("titulo", titulo);
        model.addAttribute("fechaResenia", fechaResenia);
        model.addAttribute("genero", genero);
        model.addAttribute("anioEstreno", anioEstreno);
        model.addAttribute("rating", rating);
        model.addAttribute("vigente", vigente);

        return "pelicula";
    }

    @GetMapping("/episodios")
    public String episodios(Model model) {

        String titulo1 = "Breaking Bad 🎭";
        String titulo2 = "Ataque de los titanes 🗡️";
        String titulo3 = "Ataque de los titanes 🛡️";
        String titulo4 = "Star Wars: Las Guerras Clon 🌌";
        String titulo5 = "Mr. Robot 🤖";

        String episodio1 = "Ozymandias 🏺";
        String episodio2 = "Héroe 🦸";
        String episodio3 = "Juego perfecto ⚾";
        String episodio4 = "Victoria y muerte ⚔️";
        String episodio5 = "407 Autenticación de proxy requerida 🌐";

        double rating1 = 10.0;
        double rating2 = 9.9;
        double rating3 = 9.9;
        double rating4 = 9.9;
        double rating5 = 9.9;

        model.addAttribute("titulo1", titulo1);
        model.addAttribute("titulo2", titulo2);
        model.addAttribute("titulo3", titulo3);
        model.addAttribute("titulo4", titulo4);
        model.addAttribute("titulo5", titulo5);

        model.addAttribute("episodio1", episodio1);
        model.addAttribute("episodio2", episodio2);
        model.addAttribute("episodio3", episodio3);
        model.addAttribute("episodio4", episodio4);
        model.addAttribute("episodio5", episodio5);

        model.addAttribute("rating1", rating1);
        model.addAttribute("rating2", rating2);
        model.addAttribute("rating3", rating3);
        model.addAttribute("rating4", rating4);
        model.addAttribute("rating5", rating5);

        return "episodios";
    }

}
