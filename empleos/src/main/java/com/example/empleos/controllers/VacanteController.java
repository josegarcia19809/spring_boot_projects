package com.example.empleos.controllers;

import com.example.empleos.models.Vacante;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Controller
@RequestMapping("/vacantes")
public class VacanteController {

    @GetMapping("/delete")
    public String eliminar(@RequestParam("id") int idVacante, Model model) {
        System.out.println("Borrando vacante con id: " + idVacante);
        model.addAttribute("idVacante", idVacante);
        return "vacantes/mensaje";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        System.out.println("idVacante: " + idVacante);
        model.addAttribute("idVacante", idVacante);

        // TODO: Buscar los detalles de la vacante en la BD
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
        List<Vacante> vacantes = getVacantes();
        model.addAttribute("vacantes", vacantes);
        return "tabla";
    }

    private List<Vacante> getVacantes() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        List<Vacante> listaVacantes = new LinkedList<>();

        try {
            Vacante vacante1 = new Vacante();
            vacante1.setId(1);
            vacante1.setNombre("Ingeniero Civil");
            vacante1.setDescripcion("Se solicita ingeniero para diseñar puente peatonal");
            vacante1.setFecha(sdf.parse("08-02-2019"));
            vacante1.setSalario(8500.00);
            vacante1.setDestacado(1);
            vacante1.setImagen("empresa1.png");

            Vacante vacante2 = new Vacante();
            vacante2.setId(2);
            vacante2.setNombre("Contador público");
            vacante2.setDescripcion("Empresa importante solicita el contador con cinco años de experiencia titulado");
            vacante2.setFecha(sdf.parse("09-02-2019"));
            vacante2.setSalario(12000.00);
            vacante2.setDestacado(0);
            vacante2.setImagen("empresa2.png");

            Vacante vacante3 = new Vacante();
            vacante3.setId(3);
            vacante3.setNombre("Ingeniero Eléctrico");
            vacante3.setDescripcion("Se solicita ingeniero para mantenimiento de la Instalación eléctrica");
            vacante3.setFecha(sdf.parse("10-02-2019"));
            vacante3.setSalario(10500.00);
            vacante3.setDestacado(0);
            // No tiene imagen

            Vacante vacante4 = new Vacante();
            vacante4.setId(4);
            vacante4.setNombre("Diseñador gráfico");
            vacante4.setDescripcion("Se solicita diseñador para diseñar Publicidad de la empresa");
            vacante4.setFecha(sdf.parse("11-02-2019"));
            vacante4.setSalario(7500.00);
            vacante4.setDestacado(1);
            vacante4.setImagen("empresa3.png");

            listaVacantes.add(vacante1);
            listaVacantes.add(vacante2);
            listaVacantes.add(vacante3);
            listaVacantes.add(vacante4);

        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return listaVacantes;
    }

}
