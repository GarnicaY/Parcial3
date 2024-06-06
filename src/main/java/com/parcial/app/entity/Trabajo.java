package com.parcial.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name="trabajos")
public class Trabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "id_director")
    private Director idDirector;
    @ManyToOne
    @JoinColumn(name = "id_Evaluador")
    private Evaluador idEvaluador; 
    @OneToOne
    @JoinColumn(name = "id_Estudiante", referencedColumnName = "id")
    private Estudiante idEstudiante; 
    private String estadoDirector;
    private String estadoEvaluador;
    private String link;
    private boolean disponible;

    public Trabajo() {
        super();
    }

	public Trabajo(Long id, String titulo, String descripcion, Director idDirector, Evaluador idEvaluador,
			Estudiante idEstudiante, String estadoDirector, String estadoEvaluador, String link, boolean disponible) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.idDirector = idDirector;
		this.idEvaluador = idEvaluador;
		this.idEstudiante = idEstudiante;
		this.estadoDirector = estadoDirector;
		this.estadoEvaluador = estadoEvaluador;
		this.link = link;
		this.disponible = disponible;
	}


	// Getters y setters
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

	public Director getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(Director idDirector) {
		this.idDirector = idDirector;
	}

	public Evaluador getIdEvaluador() {
		return idEvaluador;
	}

	public void setIdEvaluador(Evaluador idEvaluador) {
		this.idEvaluador = idEvaluador;
	}

	public Estudiante getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(Estudiante idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	public boolean isDisponible() {
		return disponible;
	}


	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getEstadoDirector() {
		return estadoDirector;
	}

	public void setEstadoDirector(String estadoDirector) {
		this.estadoDirector = estadoDirector;
	}

	public String getEstadoEvaluador() {
		return estadoEvaluador;
	}

	public void setEstadoEvaluador(String estadoEvaluador) {
		this.estadoEvaluador = estadoEvaluador;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}



}

