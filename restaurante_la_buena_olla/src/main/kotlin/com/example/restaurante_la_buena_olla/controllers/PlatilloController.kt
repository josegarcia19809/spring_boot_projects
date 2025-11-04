package com.example.restaurante_la_buena_olla.controllers

import com.example.restaurante_la_buena_olla.models.Platillo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class PlatilloController {
    val platillos = arrayListOf(
        Platillo(
            id = 1,
            nombre = "Chiles Rellenos",
            precio = 95.0,
            descripcion = "Deliciosos chiles poblanos rellenos de queso y bañados en salsa de tomate.",
            categoria = "Plato fuerte",
            disponible = true,
            imagen = "chiles.jpg"
        ),
        Platillo(
            id = 2,
            nombre = "Enchiladas Verdes",
            precio = 85.0,
            descripcion = "Tortillas rellenas de pollo bañadas en salsa verde y queso derretido.",
            categoria = "Plato fuerte",
            disponible = true,
            imagen = "enchiladas.jpg"
        ),
        Platillo(
            id = 3,
            nombre = "Mole Poblano",
            precio = 110.0,
            descripcion = "Pechuga de pollo bañada en mole tradicional con ajonjolí.",
            categoria = "Plato fuerte",
            disponible = true,
            imagen = "mole.jpg"
        ),
        Platillo(
            id = 4,
            nombre = "Tacos al Pastor",
            precio = 60.0,
            descripcion = "Tacos de carne de cerdo marinada con piña, cebolla y cilantro.",
            categoria = "Antojito",
            disponible = true,
            imagen = "tacos.jpg"
        ),
        Platillo(
            id = 5,
            nombre = "Torta de Milanesa",
            precio = 70.0,
            descripcion = "Torta con milanesa de res, frijoles, aguacate y jitomate.",
            categoria = "Antojito",
            disponible = true,
            imagen = "tortas.jpg"
        )
    )

    @GetMapping("/platillos")
    fun verPlatillos(model: Model): String {
        model.addAttribute("listaPlatillos", platillos)
        return "platillos" // plantilla Thymeleaf principal
    }

    // Método para ver detalle de un platillo por id
    @GetMapping("/platillos/{id}")
    fun verDetalle(@PathVariable id: Int, model: Model): String {
        val platillo = buscarPorId(id)
        return if (platillo != null) {
            model.addAttribute("platillo", platillo)
            println("platillo_detalle -> id=$id")
            "platillo_detalle" // plantilla Thymeleaf para detalle
        } else {
            model.addAttribute("mensaje", "El platillo con ID $id no existe")
            println("platillo_no_encontrado -> id=$id")
            "error" // plantilla de error
        }
    }

    // Método para buscar un platillo por id
    private fun buscarPorId(id: Int): Platillo? {
        return platillos.find { it.id == id }
    }
}