package com.parcial.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcial.app.entity.Estudiante;
import com.parcial.app.repository.EstudianteRepository;


@Controller
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping("/mostrarEstudiantes")
    public String mostrarEstudiantes(Model model) {
        List<Estudiante> listaEstudiantes = estudianteRepository.findAll();
        model.addAttribute("listaEstudiantes", listaEstudiantes);
        return "listarEstudiante";
    }

    @GetMapping("/estudiante/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "agregarEstudiante";
    }

    @PostMapping("/guardarEstudiante")
    public String guardarEstudiante(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
        return "redirect:/mostrarEstudiantes";
    }

    @GetMapping("/estudiante/editar/{id}")
    public String modificarEstudiante(@PathVariable("id") Long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if (estudiante != null) {
            model.addAttribute("estudiante", estudiante);
            return "editarEstudiante";
        } else {
            return "redirect:/mostrarEstudiantes";
        }
    }

    @GetMapping("/estudiante/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable("id") Long id) {
        estudianteRepository.deleteById(id);
        return "redirect:/mostrarEstudiantes";
    }

    @GetMapping("/buscarEstudiante")
    public String mostrarFormularioBusqueda(Model model) {
        model.addAttribute("id", Long.valueOf(0L));
        return "ConsultarEstudiante";
    }


    @PostMapping("/buscarEstudiante")
    public String buscarEstudiante(@RequestParam("id") Long id, Model model) {
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);
        if (estudiante != null) {
            model.addAttribute("estudiante", estudiante);
            return "InforEstudante";
        } else {
            model.addAttribute("mensaje", "Estudiante no encontrado");
            return "ConsultarEstudiante";
        }
    }

    @GetMapping("/loginEstudiante")
    public String mostrarFormularioLogin() {
        return "loginEstudiante";
    }

    @PostMapping("/loginEstudiante")
    public String login(@RequestParam String correo, @RequestParam String contrasena,  RedirectAttributes model) {
        Estudiante estudiante = estudianteRepository.findByCorreoAndContrasena(correo, contrasena);
        if (estudiante != null) {
            model.addFlashAttribute("estudiante", estudiante);
            return "redirect:/inicioEstudiante";
        } else {
            model.addAttribute("mensaje", "Credenciales inválidas. Inténtalo de nuevo.");
            return "loginEstudiante";
        }
    }
}
