package com.AlkemyCB.SpringJavaJwt.entity;


import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table
public class Characters {
	
	//SI LO PONGO EN IDENTITY NO ME LO AUTOINCREMENTA AUTOMATICAMENTE
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int idCharacter;
	//@Column(nullable=true)
	private String image;
	@Column
	private String name;
	@Column
	private int  age;
	@Column
	private double  weight;
	@Column
	private String history;
	
	//@JsonIgnore//si habilito me aparece no me aparece la pelicula en personajes
	@ManyToMany(fetch = FetchType.LAZY,mappedBy="personajesAsociados")
	private Set<Movie> movieOrserie/*= new HashSet<>()*/;
	
	public Characters() {}


	
	public Characters(int idCharacter, String image, String name, int age, double weight, String history) {
		super();
		this.idCharacter = idCharacter;
		this.image = image;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.history = history;
		
	}


	public Characters(String image,String name) {
		
		
		this.image = image;
		this.name = name;
		
	}
	
	

	public int getIdCharacter() {
		return idCharacter;
	}



	public void setIdCharacter(int idCharacter) {
		this.idCharacter = idCharacter;
	}




	public String getImage() {
		return image;
	}




	public void setImage(String image) {
		this.image = image;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public int getAge() {
		return age;
	}




	public void setAge(int age) {
		this.age = age;
	}




	public double getWeight() {
		return weight;
	}




	public void setWeight(double weight) {
		this.weight = weight;
	}




	public String getHistory() {
		return history;
	}




	public void setHistory(String history) {
		this.history = history;
	}




	public Set<Movie> getMovieOrserie() {
		return movieOrserie;
	}




	public void setMovieOrserie(Set<Movie> movieOrserie) {
		this.movieOrserie = movieOrserie;
	}


	
	public void AddMovie(Movie movie) {
		movieOrserie.add(movie);
	}

	public void deleteMovie(Movie movie) {
		movieOrserie.remove(movie);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCharacter;
		result = prime * result + ((movieOrserie == null) ? 0 : movieOrserie.hashCode());
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
		Characters other = (Characters) obj;
		if (idCharacter != other.idCharacter)
			return false;
		if (movieOrserie == null) {
			if (other.movieOrserie != null)
				return false;
		} else if (!movieOrserie.equals(other.movieOrserie))
			return false;
		return true;
	}








	
	
	
}
