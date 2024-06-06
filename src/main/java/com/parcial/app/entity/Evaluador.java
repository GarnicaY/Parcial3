package com.parcial.app.entity;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="evaluadores")
public class Evaluador {
	@Id
	private Long id;
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasena;
	@OneToMany(mappedBy = "idEvaluador")
    private List<Trabajo> trabajos;
	
	public Evaluador() {
		super();
		this.trabajos =  Arrays.asList();
	}
	public Evaluador(Long id, String nombre, String apellido, String correo, String contrasena) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasena = contrasena;
		this.trabajos =  Arrays.asList();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public List<Trabajo> getTrabajos() {
		return trabajos;
	}
	public void setTrabajos(List<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}
	

}
