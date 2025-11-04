package com.example.el_buen_cafe.controllers

import com.example.el_buen_cafe.models.Cafe
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class CafeController {

    @GetMapping("/cafes")
    fun verCafes(model: Model): String {
        model.addAttribute("listaCafes", obtenerListaCafes())
        return "cafes"
    }

    // Método para ver detalle de un café por id
    @GetMapping("/cafes/{id}")
    fun verDetalle(@PathVariable id: String, model: Model): String {
        val cafe = buscarPorId(id)
        return if (cafe != null) {
            model.addAttribute("cafe", cafe)
            println("cafe_detalle -> id=$id")
            "cafe_detalle" // plantilla Thymeleaf para detalle (sin .html)
        } else {
            model.addAttribute("mensaje", "El café con ID $id no existe")
            println("cafe_no_encontrado -> id=$id")
            "error" // o una plantilla de error personalizada
        }
    }

    // Método para buscar un café por id
    private fun buscarPorId(id: String): Cafe? {
        return obtenerListaCafes().find { it.id == id }
    }


    fun obtenerListaCafes(): ArrayList<Cafe> {
        var listaCafe = ArrayList<Cafe>()
        val cafe1 = Cafe("001", "Latte", "Caliente", 45.50, "carajillo.jpg")
        val cafe2 = Cafe("002", "Capuchino", "Caliente", 49.00, "cappuccino.jpg")
        val cafe3 = Cafe("003", "Espresso", "Fuerte", 39.00, "expresso.jpg")
        val cafe4 = Cafe("004", "Bombón", "Dulce", 52.00, "bombon.jpg")
        val cafe5 = Cafe("005", "Americano", "Frío", 55.50, "americano.jpg")

        listaCafe.add(cafe1)
        listaCafe.add(cafe2)
        listaCafe.add(cafe3)
        listaCafe.add(cafe4)
        listaCafe.add(cafe5)
        return listaCafe
    }
}