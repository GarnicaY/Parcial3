package com.parcial.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcial.app.entity.Director;
import com.parcial.app.repository.DirectorRepository;

@Controller
public class DirectorController {

    @Autowired
    private DirectorRepository directorRepository;

    @GetMapping("/mostrarDirectores")
    public String mostrarDirectores(Model model) {
        List<Director> listaDirectores = directorRepository.findAll();
        model.addAttribute("listaDirectores", listaDirectores);
        return "listarDirector";
    }

    @GetMapping("/director/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("director", new Director());
        return "agregarDirector";
    }

    @PostMapping("/guardarDirector")
    public String guardarDirector(Director director) {
        directorRepository.save(director);
        return "redirect:/mostrarDirectores";
    }

    @GetMapping("/director/editar/{id}")
    public String modificarDirector(@PathVariable("id") Long id, Model model) {
        Director director = directorRepository.findById(id).orElse(null);
        if (director != null) {
            model.addAttribute("director", director);
            return "editarDirector";
        } else {
            return "redirect:/mostrarDirectores";
        }
    }

    @GetMapping("/director/eliminar/{id}")
    public String eliminarDirector(@PathVariable("id") Long id) {
        directorRepository.deleteById(id);
        return "redirect:/mostrarDirectores";
    }
    @GetMapping("/loginDirector")
    public String mostrarFormularioLogin() {
        return "loginDirector";
    }

    @PostMapping("/loginDirector")
    public String login(@RequestParam String correo, @RequestParam String contrasena, RedirectAttributes model) {
        Director director = directorRepository.findByCorreoAndContrasena(correo, contrasena).orElse(null);
        if (director != null) {
        	model.addFlashAttribute("director", director);
            return "redirect:/inicioDirector";
        } else {
            model.addAttribute("mensaje", "Correo o contraseña incorrectos");
            return "loginDirector";
        }
    }
}