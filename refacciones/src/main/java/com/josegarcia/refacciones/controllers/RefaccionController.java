package com.josegarcia.refacciones.controllers;

import com.josegarcia.refacciones.models.Refaccion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/automoviles")
public class RefaccionController {

    // Ruta que "llena" la lista y la manda a la vista
    @GetMapping("/refacciones")
    public String listarRefacciones(Model model) {
        List<Refaccion> refacciones = cargarRefaccionesDemo();
        model.addAttribute("refacciones", refacciones);
        return "refacciones_lista"; // nombre del template Thymeleaf (sin .html)
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idRefaccion, Model model) {
        System.out.println("idRefaccion: " + idRefaccion);
        model.addAttribute("idRefaccion", idRefaccion);
        return "detalle";
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") int idRefaccion, Model model) {
        System.out.println("Borrando refacción con id: " + idRefaccion);
        model.addAttribute("idRefaccion", idRefaccion);
        return "borrar";
    }

    @GetMapping("/crear")
    public String crear(Model model) {
        return "formRefaccionaria";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("nombre") String nombre,
                          @RequestParam("direccion") String direccion,
                          Model model) {
        System.out.println("Nombre: " + nombre);
        System.out.println("Dirección: " + direccion);
        model.addAttribute("nombre", nombre);
        model.addAttribute("direccion", direccion);
        return "mostrarRefaccionaria";
    }


    // Método que llena un ArrayList con objetos Refaccion (demo)
    private List<Refaccion> cargarRefaccionesDemo() {
        List<Refaccion> lista = new ArrayList<>();
        lista.add(new Refaccion(1, "Filtro de aceite", "Motor", 2023, 120.50f));
        lista.add(new Refaccion(2, "Bujía iridium", "Encendido", 2022, 95.00f));
        lista.add(new Refaccion(3, "Pastillas de freno", "Frenos", 2024, 310.99f));
        lista.add(new Refaccion(4, "Amortiguador trasero", "Suspensión", 2021, 850.00f));
        return lista;
    }
}