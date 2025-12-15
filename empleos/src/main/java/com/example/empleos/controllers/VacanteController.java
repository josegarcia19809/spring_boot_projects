package com.example.empleos.controllers;

import com.example.empleos.models.Vacante;
import com.example.empleos.service.ICategoriasService;
import com.example.empleos.service.IVacanteService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/vacantes")
public class VacanteController {

    private IVacanteService vacanteService;
    private ICategoriasService categoriasService;

    // Inyección mediante el constructor
    public VacanteController(IVacanteService vacanteService,
                             ICategoriasService categoriasService) {
        this.vacanteService = vacanteService;
        this.categoriasService = categoriasService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Vacante> vacantes = vacanteService.buscarTodas();
        model.addAttribute("vacantes", vacantes);
        return "vacantes/listVacantes";
    }

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model) {
        model.addAttribute("categorias", categoriasService.buscarTodas());
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "vacantes/formVacante";
        }
        vacanteService.guardar(vacante);
        redirectAttributes.addFlashAttribute("msg", "Registro guardado exitosamente");
        System.out.println(vacante);
        return "redirect:/vacantes/index";
    }

//    @PostMapping("/save")
//    public String guardar(@RequestParam("nombre") String nombre,
//                          @RequestParam("descripcion") String descripcion,
//                          @RequestParam("estatus") String estatus,
//                          @RequestParam("fecha") String fecha,
//                          @RequestParam("destacado") int destacado,
//                          @RequestParam("salario") double salario,
//                          @RequestParam("detalles") String detalles){
//        System.out.println(nombre);
//        System.out.println(descripcion);
//        System.out.println(estatus);
//        System.out.println(fecha);
//        System.out.println(destacado);
//        System.out.println(salario);
//        System.out.println(detalles);
//
//        return "vacantes/listVacantes";
//    }


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
        return "vacantes/detalle";
    }

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> vacantes = vacanteService.buscarTodas();
        model.addAttribute("vacantes", vacantes);
        return "tabla";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
