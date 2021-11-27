package com.AlkemyCB.SpringJavaJwt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.AlkemyCB.SpringJavaJwt.entity.Movie;
import com.AlkemyCB.SpringJavaJwt.entity.Characters;
import com.AlkemyCB.SpringJavaJwt.model.CharacterModel;

public interface ICharacterService {
	
	
	public Characters saveOrUpdateCharacter(Characters character);
	
	public boolean remove(int idcharacter);
	
	public Optional<Characters>findByIdCharacter(int id);
	
	public List<Characters> findByName(String name);
	
	public List<Characters> findByAge(int age);
	
	public List<Characters> findByWeight(double weight);
	
	public Movie findByIdMovie(int id);
	
	public List<Characters> listDetails();
		
	public List<CharacterModel> listCharacter();
		
	public Characters findByNameAndAge(String name,int age);
		
	public Characters findByNameAndPeso(String name,double peso);
		
	public Characters findByNameAndMovie(String name,int id) ;
	
	public void addMovieCharacter(Movie movie, Characters person);
		
		
		

}
