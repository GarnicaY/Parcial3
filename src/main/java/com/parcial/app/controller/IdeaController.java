package com.parcial.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.parcial.app.entity.Idea;
import com.parcial.app.repository.IdeaRepository;

@Controller
public class IdeaController {

	@Autowired
	private IdeaRepository ideaRepository;

	@GetMapping("/mostrarIdeas4")
	public String mostrarIdeas(Model model) {
		List<Idea> listaIdeas = ideaRepository.findAll();
		model.addAttribute("listaIdeas", listaIdeas);
		return "listarIdea";
	}

	@GetMapping("/listarIdeaEstudiantegg")
	public String ideaEstudiante(Model model) {
		List<Idea> listaIdeaEstudiante = ideaRepository.findAll();
		model.addAttribute("listaIdeaEstudiante", listaIdeaEstudiante);
		return "listarideaEstudiantes";
	}

	@GetMapping("/idea/agregarr")
	public String mostrarFormulario(Model model) {
		model.addAttribute("idea", new Idea());
		return "agregarIdea";
	}

	@PostMapping("/guardarIdea4")
	public String guardarIdea(Idea idea) {
		ideaRepository.save(idea);
		return "redirect:/mostrarIdeas4";
	}

	@GetMapping("/idea/editar/{id}")
	public String modificarIdea(@PathVariable("id") Long id, Model model) {
		Idea idea = ideaRepository.findById(id).orElse(null);
		if (idea != null) {
			model.addAttribute("idea", idea);
			return "editarIdea";
		} else {
			return "redirect:/mostrarIdeas4";
		}
	}

	@GetMapping("/idea/eliminar/{id}")
	public String eliminarIdea(@PathVariable("id") Long id) {
		ideaRepository.deleteById(id);
		return "redirect:/mostrarIdeas4";
	}
}