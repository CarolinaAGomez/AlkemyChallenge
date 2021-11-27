package com.AlkemyCB.SpringJavaJwt.model;



public class CharacterModel {
	

	private String imagen;

	private String nombre;
	
	public CharacterModel() {}

	public CharacterModel(String imagen, String nombre) {
		super();
		this.imagen = imagen;
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	};
	
	
	

}
