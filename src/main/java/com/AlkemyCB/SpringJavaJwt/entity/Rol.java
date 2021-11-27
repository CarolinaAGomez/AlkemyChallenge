package com.AlkemyCB.SpringJavaJwt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Rol {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idRol;
	
	@Column
	private String name;
	
	
	public Rol() {}
	
	public Rol( String name) {
		this.name=name;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
