package com.parcial.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcial.app.entity.Director;
import com.parcial.app.entity.Estudiante;
import com.parcial.app.entity.Evaluador;
import com.parcial.app.entity.Trabajo;
import com.parcial.app.repository.DirectorRepository;
import com.parcial.app.repository.EstudianteRepository;
import com.parcial.app.repository.EvaluadorRepository;
import com.parcial.app.repository.TrabajoRepository;


@Controller
public class TrabajoController {

    @Autowired
    private TrabajoRepository trabajoRepository;
    
    @Autowired
    private EstudianteRepository estudianteRepository;
    
    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private EvaluadorRepository evaluadorRepository;

    @GetMapping("/mostrarIdeas")
    public String mostrarTrabajos(Model model) {
        List<Trabajo> listaTrabajos = trabajoRepository.findAll();
        model.addAttribute("listaTrabajos", listaTrabajos);
        return "listarIdea";
    }
    
    @GetMapping("/gestionarTrabajos")
    public String gestionarTrabajos(Model model) {
        List<Trabajo> listaTrabajos = trabajoRepository.findByDisponibleFalse();
        model.addAttribute("listaTrabajos", listaTrabajos);
        return "listarTrabajoCoordi";
    }
    
    @GetMapping("/listarIdeaEstudiante/{id}")
	public String ideaEstudiante(@PathVariable("id") Long id, Model model) {
    	Estudiante estudiante = new Estudiante();
    	estudiante.setId(id);
		List<Trabajo> listaTrabajoEstudiante = trabajoRepository.findByDisponibleTrue();
		model.addAttribute("listaTrabajoEstudiante", listaTrabajoEstudiante);
		model.addAttribute("estudiante", estudiante);
		return "listarideaEstudiantes";
	}

    @GetMapping("/listarTrabajosEstudiante/{id}")
    public String listarTrabajosEstudiante(@PathVariable Long id, Model model, RedirectAttributes modelo) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);
        if(estudianteOptional.isPresent()){
            Trabajo trabajo = trabajoRepository.findByIdEstudianteId(id);
            if(trabajo != null) {
            	System.out.println("Trabajo Existe " + estudianteOptional.get());
            	 modelo.addFlashAttribute("estudiante", estudianteOptional.get());
                model.addAttribute("trabajo", trabajo);
            } else {
            	System.out.println("Trabajo No Existe ");
                model.addAttribute("message", "No se encontró trabajo para el estudiante con ID " + id);
            }
        } else {
            model.addAttribute("message", "No se encontró el estudiante con ID " + id);
        }
        return "listarTrabajoEstudinate";
    }
    
    @GetMapping("/asignarDirectorEvaluador/{id}")
    public String mostrarFormularioAsignacion(@PathVariable("id") Long id, Model model) {
        Trabajo trabajo = trabajoRepository.findById(id).orElse(null);
        List<Director> listaDirectores = directorRepository.findAll();
        List<Evaluador> listaEvaluadores = evaluadorRepository.findAll();
        model.addAttribute("trabajo", trabajo);
        model.addAttribute("listaDirectores", listaDirectores);
        model.addAttribute("listaEvaluadores", listaEvaluadores);
        return "asignarDirectorEvaluador";
    }

    @PostMapping("/guardarAsignacion")
    public String guardarAsignacion(@ModelAttribute Trabajo trabajo) {
    	System.out.println("Trabajo guardar " +trabajo.getIdEstudiante() + ":" + trabajo.getIdDirector() + ":" + trabajo.getIdEvaluador());
    	Estudiante idEstudiante = trabajo.getIdEstudiante();
        Trabajo trabajoExistente = trabajoRepository.findByIdEstudiante(idEstudiante);
        if (trabajoExistente != null) {
        	System.out.println("Intenta Guardar" + trabajo.getIdDirector());
            trabajoExistente.setIdDirector(trabajo.getIdDirector());
            trabajoExistente.setIdEvaluador(trabajo.getIdEvaluador());
            trabajoExistente.setTitulo(trabajo.getTitulo());
            trabajoExistente.setDescripcion(trabajo.getDescripcion());
            trabajoExistente.setDisponible(trabajo.isDisponible());
            trabajoExistente.setEstadoDirector(trabajo.getEstadoDirector());
            trabajoExistente.setEstadoEvaluador(trabajo.getEstadoEvaluador());
            Director director = trabajo.getIdDirector();
            director.getTrabajos().add(trabajoExistente);
            directorRepository.save(director);
            trabajoRepository.save(trabajoExistente);
        } else {
            trabajoRepository.save(trabajo);
        }
        return "redirect:/gestionarTrabajos";
    }

    @GetMapping("/trabajo/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("trabajo", new Trabajo());      
        return "agregarIdea";
    }

    @PostMapping("/guardarTrabajo")
    public String guardarTrabajo(@ModelAttribute Trabajo trabajo) {
    	trabajo.setDisponible(true);
        trabajoRepository.save(trabajo);     
        return "redirect:/mostrarIdeas";
    }

    @GetMapping("/trabajo/editar/{id}")
    public String modificarTrabajo(@PathVariable("id") Long id, Model model) {
        Trabajo trabajo = trabajoRepository.findById(id).orElse(null);
        if (trabajo != null) {
            model.addAttribute("trabajo", trabajo);
            return "editarIdea";
        } else {
            return "redirect:/mostrarTrabajos";
        }
    }

    @GetMapping("/trabajo/eliminar/{id}")
    public String eliminarTrabajo(@PathVariable("id") Long id) {
        trabajoRepository.deleteById(id);
        return "redirect:/mostrarTrabajos";
    }
    
    @PostMapping("/trabajo/crear")
    public String crearTrabajo(@ModelAttribute Trabajo trabajo) {
        trabajo.setEstadoDirector("Pendiente");
        trabajo.setEstadoEvaluador("Pendiente");
        trabajoRepository.save(trabajo);
        return "redirect:/mostrarTrabajos";
    }
    @PostMapping("/elegirTrabajo")
    public String elegirIdea(@RequestParam("trabajoId") Long trabajoId, @RequestParam("estudianteId") Long estudianteId, RedirectAttributes redirect) {
        Trabajo trabajo = trabajoRepository.findById(trabajoId).orElse(null);
        Estudiante estudiante = estudianteRepository.findById(estudianteId).orElse(null);
        if (trabajo != null && estudiante != null) {
            trabajo.setDisponible(false);
            trabajo.setIdEstudiante(estudiante);
            trabajoRepository.save(trabajo);
        }
        redirect.addFlashAttribute("estudiante", estudiante);
        return "redirect:/inicioEstudiante";
    }
    
    @GetMapping("/listarTrabajosDirector/{id}")
    public String listarTrabajosDirector(@PathVariable Long id, Model model) {
        Optional<Director> directorOptional = directorRepository.findById(id);
        System.out.println("Id del director: " + id);
        if(directorOptional.isPresent()){
        	System.out.println("director Escogido" + directorOptional.get());
            List<Trabajo> trabajos = directorOptional.get().getTrabajos();
            System.out.println("Lista DE trabajos: " + trabajos.size());
            if(trabajos != null && !trabajos.isEmpty()) {
                model.addAttribute("trabajos", trabajos);
            } else {
                model.addAttribute("message", "No se encontraron trabajos para el director con ID " + id);
            }
        } else {
        	System.out.println("No se encontró");
            model.addAttribute("message", "No se encontró el director con ID " + id);
        }
        return "aprobarProyectoDirector";
    }

    @PostMapping("/aprobarReprobarProyecto")
    public String aprobarReprobarProyecto(@RequestParam Long idProyecto, @RequestParam String accion) {
        Trabajo trabajo = trabajoRepository.findById(idProyecto).orElse(null);
        if (trabajo != null) {
            if ("aprobar".equals(accion)) {
                trabajo.setEstadoDirector("aprobado");
            } else if ("reprobar".equals(accion)) {
                trabajo.setEstadoDirector("reprobado");
            }
            trabajoRepository.save(trabajo);
            return "redirect:/listarTrabajosDirector/" + trabajo.getIdDirector().getId();
        } else {
            return "redirect:/listarTrabajosDirector/";
        }
    }
    
    @GetMapping("/listarTrabajosEvaluador/{id}")
    public String listarTrabajosEvaluador(@PathVariable Long id, Model model) {
        Optional<Evaluador> evaluadorOptional = evaluadorRepository.findById(id);
        System.out.println("Id del evaluador: " + id);
        if(evaluadorOptional.isPresent()){
            System.out.println("Evaluador escogido: " + evaluadorOptional.get());
            List<Trabajo> trabajos = evaluadorOptional.get().getTrabajos();
            System.out.println("Lista de trabajos: " + trabajos.size());
            if(trabajos != null && !trabajos.isEmpty()) {
                model.addAttribute("trabajos", trabajos);
            } else {
                model.addAttribute("message", "No se encontraron trabajos para el evaluador con ID " + id);
            }
        } else {
            System.out.println("No se encontró el evaluador");
            model.addAttribute("message", "No se encontró el evaluador con ID " + id);
        }
        return "aprobarProyectoEvaluador";
    }
    
    @PostMapping("/aprobarReprobarProyectoEvaluador")
    public String aprobarReprobarProyectoEvaluador(@RequestParam Long idProyecto, @RequestParam String accion) {
        Trabajo trabajo = trabajoRepository.findById(idProyecto).orElse(null);
        if (trabajo != null) {
            if ("aprobar".equals(accion)) {
                trabajo.setEstadoEvaluador("aprobado");
            } else if ("reprobar".equals(accion)) {
                trabajo.setEstadoEvaluador("reprobado");
            }
            trabajoRepository.save(trabajo);
            return "redirect:/listarTrabajosEvaluador/" + trabajo.getIdEvaluador().getId();
        } else {
            return "redirect:/listarTrabajosEvaluador/";
        }
    }
    
    @PostMapping("/subirTrabajo")
    public String subirTrabajo(@RequestParam Long id, @RequestParam String link, Model model, RedirectAttributes modelo) {
        Optional<Trabajo> trabajoOptional = trabajoRepository.findById(id);
        if (trabajoOptional.isPresent()) {
        	modelo.addFlashAttribute("trabajo", trabajoOptional);
            Trabajo trabajo = trabajoOptional.get();
            trabajo.setLink(link);
            trabajoRepository.save(trabajo);
            model.addAttribute("message", "Link actualizado exitosamente.");
        } else {
            model.addAttribute("error", "No se encontró el trabajo con ID " + id);
        }
        return "redirect:/listarTrabajosEstudiante/" + trabajoOptional.get().getIdEstudiante().getId();
    }

}