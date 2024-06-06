package com.parcial.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name="ideas")
public class Idea {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String titulo;
	private String descripcion;
	
	public Idea() {
		super();
	}
	
	public Idea(Long id, String titulo, String descripcion) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
