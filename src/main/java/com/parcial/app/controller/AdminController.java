package com.parcial.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.parcial.app.entity.Admin;
import com.parcial.app.repository.AdminRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/mostrarAdmins")
    public String mostrarAdmins(Model model) {
        List<Admin> listaAdmins = adminRepository.findAll();
        model.addAttribute("listaAdmins", listaAdmins);
        return "listarAdmin";
    }

    @GetMapping("/admin/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("admin", new Admin());
        return "agregarAdmin";
    }

    @PostMapping("/guardarAdmin")
    public String guardarAdmin(Admin admin) {
        adminRepository.save(admin);
        return "redirect:/mostrarAdmins";
    }

    @GetMapping("/admin/editar/{id}")
    public String modificarAdmin(@PathVariable("id") Long id, Model model) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
            model.addAttribute("admin", admin);
            return "editarAdmin";
        } else {
            return "redirect:/mostrarAdmins";
        }
    }

    @GetMapping("/admin/eliminar/{id}")
    public String eliminarAdmin(@PathVariable("id") Long id) {
        adminRepository.deleteById(id);
        return "redirect:/mostrarAdmins";
    }
}
