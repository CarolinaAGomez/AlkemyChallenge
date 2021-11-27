package com.AlkemyCB.SpringJavaJwt.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table
public class Movie {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int idMovie;
	@Column
	private String image;
	@Column
	private String title;
	@Column
	private LocalDate creationDate;
	@Column
	@Min(value =1 , message="TIENE QUE SER MAYOR A 1")
	@Max(value =5 , message="TIENE QUE SER MENOR A 5")
	private int score; // (del 1 al 5).
	
	@JsonIgnore //SI PONGO ESTO en el listado de eprsonaje no es un bucle infinito
	@ManyToMany(fetch = FetchType.LAZY/*, cascade= { CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH }*/)
            
	@JoinTable(
		name="character_movie", 
		joinColumns=@JoinColumn (name="movie_id"), //no necesarimanete tiene que ser igual a como esta en la clase
		inverseJoinColumns= @JoinColumn(name="character_id")
		)
	private Set<Characters> personajesAsociados;;
	
	@ManyToOne(fetch = FetchType.EAGER) //Si pongo Lazy y borro el set/get de genero me da error, asi esta borrado el set//get de genero y no me da error
	@JoinColumn(name="gender_id")
	private Gender gender;
	
	public int getIdMovie() {
		return idMovie;
	}


	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}


	public Movie () {}


	public Movie( String image, String title, LocalDate creationDate,int score, Gender gender) {
		super();
		
		this.image = image;
		this.title = title;
		this.creationDate = creationDate;
		this.score=score;
		this.gender = gender;
	}


	public Movie(String image,String title,LocalDate creationDate) {
		this.title=title;
		this.title=title;
		this.creationDate=creationDate;
	}
	
	

	
	public Movie(int idMovie, String image, String title, LocalDate creationDate,int score,Gender gender,Set<Characters> personajesAsociados) {
		super();
		this.idMovie = idMovie;
		this.image = image;
		this.title = title;
		this.creationDate = creationDate;
		this.score=score;
		this.personajesAsociados = personajesAsociados;
		this.gender = gender;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Set<Characters> getPersonajesAsociados() {
		return personajesAsociados;
	}

	public void setPersonajesAsociados(Set<Characters> personajesAsociados) {
		this.personajesAsociados = personajesAsociados;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void addPersonaje(Characters personaje) {
		personajesAsociados.add(personaje);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMovie;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (idMovie != other.idMovie)
			return false;
		return true;
	}

	
	
	public void deleteCharacter(Characters character) {
		personajesAsociados.remove(character);
	}


	
}
