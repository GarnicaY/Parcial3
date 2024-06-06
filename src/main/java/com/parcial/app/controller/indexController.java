package com.parcial.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.parcial.app.entity.Estudiante;
import com.parcial.app.repository.EstudianteRepository;

@Controller
public class indexController {
	
	 @Autowired
	  private EstudianteRepository estudianteRepository;

    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/inicioAdmin")
    public String inicioAdmin(Model model) {
        return "inicioAdmin";
    }
    
    @GetMapping("/inicioEstudiante/{id}")
    public String inicioEstudianteId(@PathVariable("id") Long estudianteId, Model model) {
    	Estudiante estudiante = estudianteRepository.findById(estudianteId).orElse(null);
    	model.addAttribute("estudiante", estudiante);
    	System.out.println("Estudiante id: " +estudiante.getId());
        return "inicioEstudiante";
    }

    @GetMapping("/inicioEstudiante")
    public String inicioEstudiante(Model model) {
        return "inicioEstudiante";
    }
    

    @GetMapping("/inicioCoordinador")
    public String inicioCoordinador(Model model) {
        return "inicioCoordinador";
    }

    @GetMapping("/inicioDirector")
    public String inicioDirector(Model model) {
        return "inicioDirector";
    }

    @GetMapping("/inicioEvaluador")
    public String inicioEvaluador(Model model) {
        return "inicioEvaluador";
    }
}
