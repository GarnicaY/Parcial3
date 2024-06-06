package com.parcial.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.app.entity.Evaluador;


public interface EvaluadorRepository extends JpaRepository<Evaluador, Long>{
    Optional<Evaluador> findByCorreoAndContrasena(String correo, String contrasena);
}
