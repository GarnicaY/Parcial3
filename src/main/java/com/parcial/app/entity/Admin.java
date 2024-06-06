package com.parcial.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name="admins")
public class Admin {
	
	@Id
	private Long id;
	private String correo;
	private String contrasena;
	
	public Admin() {
		super();
	}
	public Admin(Long id, String correo, String contrasena) {
		super();
		this.id = id;
		this.correo = correo;
		this.contrasena = contrasena;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	
}
