package com.parcial.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcial.app.entity.Evaluador;
import com.parcial.app.repository.EvaluadorRepository;

@Controller
public class EvaluadorController {

    @Autowired
    private EvaluadorRepository evaluadorRepository;

    @GetMapping("/mostrarEvaluadores")
    public String mostrarEvaluadores(Model model) {
        List<Evaluador> listaEvaluadores = evaluadorRepository.findAll();
        model.addAttribute("listaEvaluadores", listaEvaluadores);
        return "listarEvaluador";
    }

    @GetMapping("/evaluador/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("evaluador", new Evaluador());
        return "agregarEvaluador";
    }

    @PostMapping("/guardarEvaluador")
    public String guardarEvaluador(Evaluador evaluador) {
        evaluadorRepository.save(evaluador);
        return "redirect:/mostrarEvaluadores";
    }

    @GetMapping("/evaluador/editar/{id}")
    public String modificarEvaluador(@PathVariable("id") Long id, Model model) {
        Evaluador evaluador = evaluadorRepository.findById(id).orElse(null);
        if (evaluador != null) {
            model.addAttribute("evaluador", evaluador);
            return "editarEvaluador";
        } else {
            return "redirect:/mostrarEvaluadores";
        }
    }

    @GetMapping("/evaluador/eliminar/{id}")
    public String eliminarEvaluador(@PathVariable("id") Long id) {
        evaluadorRepository.deleteById(id);
        return "redirect:/mostrarEvaluadores";
    }
    @GetMapping("/loginEvaluador")
    public String mostrarFormularioLogin() {
        return "loginEvaluador";
    }

    @PostMapping("/loginEvaluador")
    public String login(@RequestParam String correo, @RequestParam String contrasena, RedirectAttributes model) {
        Evaluador evaluador = evaluadorRepository.findByCorreoAndContrasena(correo, contrasena).orElse(null);
        if (evaluador != null) {
        	model.addFlashAttribute("evaluador", evaluador);
            return "redirect:/inicioEvaluador";
        } else {
            model.addAttribute("mensaje", "Correo o contraseña incorrectos");
            return "loginEvaluador";
        }
    }
}
