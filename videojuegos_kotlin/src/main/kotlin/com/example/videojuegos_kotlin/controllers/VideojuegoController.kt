package com.example.videojuegos_kotlin.controllers

import com.example.videojuegos_kotlin.models.Videojuego
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class VideojuegoController {
    @GetMapping("/videojuegos")
    fun verVideojuegos(model: Model): String {
        model.addAttribute("titulo", "Listado de videojuegos 🎮")
        model.addAttribute("listaVideojuegos", obtenerListaVideojuegos())
        return "videojuegos"
    }

    fun obtenerListaVideojuegos(): ArrayList<Videojuego> {
        val juego1 = Videojuego("The legend of Zelda", "Aventura", "Nintendo Switch", 2017, "zelda.jpg")
        val juego2 = Videojuego("Halo Infinite", "Shooter", "Xbox Series X", 2021, "halo_infinite.jpg")
        val juego3 = Videojuego("Among Us", "Party", "PC", 2018, "among_us.jpg")
        val juego4 = Videojuego("Minecraft", "Sandbox", "PC", 2011, "minecraft.jpg")
        val juego5 = Videojuego("Fortnite", "Battle Royale", "PC", 2017, "fortnite.jpg")
        val juego6 = Videojuego(
            "Super Mario Odyssey", "Aventura", "Nintendo Switch", 2017,
            "super_mario_odyssey.png"
        )
        val juego7 = Videojuego("Cyberpunk 2077", "RPG", "PlayStation 4", 2020, "cyberpunk2077.jpg")
        val juego8 = Videojuego("The Witcher 3", "RPG", "PC", 2015, "witcher3.png")
        val juego9 = Videojuego("Call of Duty: Warzone", "Shooter", "PlayStation 4", 2020, "warzone.jpg")
        val juego10 = Videojuego(
            "Animal Crossing", "Simulación", "Nintendo Switch", 2020,
            "animal_crossing.png"
        ) //jpeg
        val juego11 = Videojuego(
            "Resident Evil Village", "Horror", "PlayStation 4", 2021,
            "resident_evil_village.png"
        )
        val juego12 = Videojuego("God of War", "Acción", "PlayStation 4", 2018, "god_of_war.jpg")


        // Crear lista de videojuegos
        val listaVideojuegos = ArrayList<Videojuego>()

        listaVideojuegos.add(juego1)
        listaVideojuegos.add(juego2)
        listaVideojuegos.add(juego3)
        listaVideojuegos.add(juego4)
        listaVideojuegos.add(juego5)
        listaVideojuegos.add(juego6)
        listaVideojuegos.add(juego7)
        listaVideojuegos.add(juego8)
        listaVideojuegos.add(juego9)
        listaVideojuegos.add(juego10)
        listaVideojuegos.add(juego11)
        listaVideojuegos.add(juego12)

        return listaVideojuegos
    }
}