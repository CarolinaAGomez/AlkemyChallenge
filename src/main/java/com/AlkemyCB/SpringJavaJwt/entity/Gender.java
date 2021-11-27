package com.AlkemyCB.SpringJavaJwt.entity;


import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Gender {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public int idGender;
	@Column
	private String  name;
	@Column
	private String image;
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy="gender")
	private Set<Movie> moviesForGender;
	
	public Gender () {}

	

	public Gender(int idGender, String name, String image) {
		super();
		this.idGender = idGender;
		this.name = name;
		this.image = image;
	}

	public Gender(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}



	public int getIdGender() {
		return idGender;
	}



	public void setIdGender(int idGender) {
		this.idGender = idGender;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public Set<Movie> getMoviesForGender() {
		return moviesForGender;
	}



	public void setMoviesForGender(Set<Movie> moviesForGender) {
		this.moviesForGender = moviesForGender;
	}

	
	
	
}
