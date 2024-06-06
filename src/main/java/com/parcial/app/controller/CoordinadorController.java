package com.parcial.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.parcial.app.entity.Coordinador;
import com.parcial.app.repository.CoordinadorRepository;

@Controller
public class CoordinadorController {

    @Autowired
    private CoordinadorRepository coordinadorRepository;

    @GetMapping("/mostrarCoordinadores")
    public String mostrarCoordinadores(Model model) {
        List<Coordinador> listaCoordinadores = coordinadorRepository.findAll();
        model.addAttribute("listaCoordinadores", listaCoordinadores);
        return "listarCoordin";
    }

    @GetMapping("/coordinador/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("coordinador", new Coordinador());
        return "agregarCoordi";
    }

    @PostMapping("/guardarCoordinador")
    public String guardarCoordinador(Coordinador coordinador) {
        coordinadorRepository.save(coordinador);
        return "redirect:/mostrarCoordinadores";
    }

    @GetMapping("/coordinador/editar/{id}")
    public String modificarCoordinador(@PathVariable("id") Long id, Model model) {
        Coordinador coordinador = coordinadorRepository.findById(id).orElse(null);
        if (coordinador != null) {
            model.addAttribute("coordinador", coordinador);
            return "editarCoordi";
        } else {
            return "redirect:/mostrarCoordinadores";
        }
    }

    @GetMapping("/coordinador/eliminar/{id}")
    public String eliminarCoordinador(@PathVariable("id") Long id) {
        coordinadorRepository.deleteById(id);
        return "redirect:/mostrarCoordinadores";
    }
    @GetMapping("/loginCoordinador")
    public String mostrarFormularioLogin() {
        return "loginCoordinador";
    }

    @PostMapping("/loginCoordinador")
    public String login(@RequestParam String correo, @RequestParam String contrasena, Model model) {
        Coordinador coordinador = coordinadorRepository.findByCorreoAndContrasena(correo, contrasena).orElse(null);
        if (coordinador != null) {
            return "redirect:/inicioCoordinador";
        } else {
            model.addAttribute("mensaje", "Correo o contraseña incorrectos");
            return "loginCoordinador";
        }
    }
}

