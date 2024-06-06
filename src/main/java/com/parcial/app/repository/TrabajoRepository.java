package com.parcial.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parcial.app.entity.Estudiante;
import com.parcial.app.entity.Trabajo;


public interface TrabajoRepository extends JpaRepository<Trabajo, Long>{
	List<Trabajo> findByDisponibleTrue();
	List<Trabajo> findByDisponibleFalse();
	List<Trabajo> findByIdDirector_Id(Long idDirector);
	List<Trabajo> findByIdEvaluador_Id(Long idEvaluador);
    Trabajo findByIdEstudiante(Estudiante estudiante);
    Trabajo findByIdEstudianteId(Long idEstudiante);
}
