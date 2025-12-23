package com.example.empleos.controllers;


import com.example.empleos.models.Solicitud;
import com.example.empleos.models.Usuario;
import com.example.empleos.models.Vacante;
import com.example.empleos.service.ISolicitudesService;
import com.example.empleos.service.IUsuariosService;
import com.example.empleos.service.IVacanteService;
import com.example.empleos.util.Utileria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Value("${empleosapp.ruta.cv}")
    private String rutaCV;

    @Autowired
    private IVacanteService vacanteService;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private ISolicitudesService solicitudesService;

    @GetMapping("/indexPaginate")
    public String index(Model model, Pageable pageable) {
        Page<Solicitud> lista = solicitudesService.buscarTodos(pageable);
        model.addAttribute("solicitudes", lista);
        return "solicitudes/listSolicitudes";
    }

    @GetMapping("/create/{idVacante}")
    public String create(Solicitud solicitud, @PathVariable("idVacante") Integer idVacante,
                         Model model) {
        Vacante vacante = vacanteService.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "solicitudes/formSolicitud";
    }

    @PostMapping("/save")
    public String save(Solicitud solicitud, BindingResult result, Model model,
                       @RequestParam("archivoCV") MultipartFile archivoCV,
                       Authentication authentication,
                       RedirectAttributes redirectAttributes) {

        String username = authentication.getName();

        if (result.hasErrors()) {
            System.out.println("Errores existieron");
            return "solicitudes/formSolicitud";
        }

        if (!archivoCV.isEmpty()) {
            String nombreArchivo = Utileria.guardarArchivo(archivoCV, rutaCV);
            if (nombreArchivo != null) {
                solicitud.setArchivo(nombreArchivo);
            }
        }

        Usuario usuario = usuariosService.buscarPorUsername(username);
        solicitud.setUsuario(usuario);

        solicitudesService.guardar(solicitud);
        redirectAttributes.addFlashAttribute("msg", "Solicitud guardada correctamente");

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id,
                         RedirectAttributes redirectAttributes) {
        solicitudesService.eliminar(id);
        redirectAttributes.addFlashAttribute("msg", "Solicitud eliminada correctamente");
        return "redirect:/solicitudes/indexPaginate";
    }
}
