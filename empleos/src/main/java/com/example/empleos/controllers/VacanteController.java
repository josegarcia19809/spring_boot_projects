package com.example.empleos.controllers;

import com.example.empleos.models.Vacante;
import com.example.empleos.service.IVacanteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/vacantes")
public class VacanteController {

    private IVacanteService vacanteService;

    // Inyección mediante el constructor
    public VacanteController(IVacanteService vacanteService) {
        this.vacanteService = vacanteService;
    }

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") int idVacante, Model model) {
        System.out.println("Borrando vacante con id: " + idVacante);
        model.addAttribute("idVacante", idVacante);
        return "vacantes/mensaje";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        // TODO: Buscar los detalles de la vacante en la BD, o en este caso en la listaVacantes
        Vacante vacanteBuscado = vacanteService.buscarPorId(idVacante);
        System.out.println(vacanteBuscado);
        model.addAttribute("vacante", vacanteBuscado);

        return "vacantes/detalle";
    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model) {
        Vacante vacante = new Vacante();
        vacante.setNombre("Ingeniero de comunicaciones");
        vacante.setDescripcion("se solicita Ingeniero para dar soporte a Intranet");
        vacante.setFecha(new Date());
        vacante.setSalario(9700.00);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> vacantes = vacanteService.buscarTodas();
        model.addAttribute("vacantes", vacantes);
        return "tabla";
    }

}
