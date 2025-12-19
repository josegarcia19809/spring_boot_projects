package com.example.empleos.controllers;

import com.example.empleos.models.Categoria;
import com.example.empleos.service.ICategoriasService;
import com.example.empleos.service.VacanteServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriasController {

    private final VacanteServiceImpl vacanteServiceImpl;
    private ICategoriasService categoriasService;

    public CategoriasController(ICategoriasService categoriasService, VacanteServiceImpl vacanteServiceImpl) {
        this.categoriasService = categoriasService;
        this.vacanteServiceImpl = vacanteServiceImpl;
    }

    // @GetMapping("/index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarIndex(Model model){
        List<Categoria> categorias = categoriasService.buscarTodas();
        model.addAttribute("categorias", categorias);
        return "categorias/listCategorias";
    }

    // @GetMapping("/create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String crear(Categoria categoria, Model model){
        return "categorias/formCategoria";
    }

    // @PostMapping("/save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String guardar(Categoria categoria, BindingResult result,
                          RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "categorias/formCategoria";
        }
        categoriasService.guardar(categoria);
        redirectAttributes.addFlashAttribute("msg", "Categoria guardado exitosamente");
        return "redirect:/categorias/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idCategoria,
                           RedirectAttributes redirectAttributes) {
        System.out.println("Borrando categoria con id: " + idCategoria);
        categoriasService.eliminar(idCategoria);
        redirectAttributes.addFlashAttribute("msg", "Registro eliminado exitosamente");
        return "redirect:/categorias/index";
    }

}

