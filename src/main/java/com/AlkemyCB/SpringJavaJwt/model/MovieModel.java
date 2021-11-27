package com.AlkemyCB.SpringJavaJwt.model;

import java.time.LocalDate;


public class MovieModel {
	
	
	private String imagen;
	private String titulo;
	private LocalDate fechaCreacion;
	

	
	public MovieModel() {}
	
	public MovieModel(String imagen,String titulo,LocalDate fechaCreacion) {
		this.imagen=imagen;
		this.titulo=titulo;
		this.fechaCreacion=fechaCreacion;
	}
	
	


	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	
	
	
}
