package com.AlkemyCB.SpringJavaJwt.model;

public class Character_Movie {
	
	private int idCharacter;
	private int idMovie;
	
	
	public Character_Movie(int idCharacter, int idMovie) {
		super();
		this.idCharacter = idCharacter;
		this.idMovie = idMovie;
	}
	
	public Character_Movie() {}

	public int getIdCharacter() {
		return idCharacter;
	}

	public void setIdCharacter(int idCharacter) {
		this.idCharacter = idCharacter;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

}
