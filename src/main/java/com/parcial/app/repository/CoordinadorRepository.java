package com.parcial.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.parcial.app.entity.Coordinador;


public interface CoordinadorRepository extends JpaRepository<Coordinador, Long>{
    Optional<Coordinador> findByCorreoAndContrasena(String correo, String contrasena);
}