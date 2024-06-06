package com.parcial.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.app.entity.Director;


public interface DirectorRepository extends JpaRepository<Director, Long>{
    Optional<Director> findByCorreoAndContrasena(String correo, String contrasena);

}