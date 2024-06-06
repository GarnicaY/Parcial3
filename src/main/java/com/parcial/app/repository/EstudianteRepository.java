package com.parcial.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.app.entity.Estudiante;


public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
    Estudiante findByCorreoAndContrasena(String correo, String contrasena);

}