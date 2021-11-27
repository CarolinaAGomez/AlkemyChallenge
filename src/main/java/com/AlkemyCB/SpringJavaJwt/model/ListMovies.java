package com.AlkemyCB.SpringJavaJwt.model;

import java.time.LocalDate;
import java.util.Set;

import com.AlkemyCB.SpringJavaJwt.entity.Gender;

public class ListMovies {
	
	private int idMovie;
	private String imagen;
	private String titulo;
	private LocalDate fechaCreacion;
	private int score;
	private Set<CharacterModel> listCharacter;
	private Gender gender;
	
	public ListMovies(int idMovie, String imagen, String titulo, LocalDate fechaCreacion, int score, Gender gender,
			Set<CharacterModel> listCharacter) {
		super();
		this.idMovie = idMovie;
		this.imagen = imagen;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.score = score;
		this.listCharacter = listCharacter;
		this.gender = gender;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Set<CharacterModel> getListCharacter() {
		return listCharacter;
	}

	public void setListCharacter(Set<CharacterModel> listCharacter) {
		this.listCharacter = listCharacter;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
	

}
