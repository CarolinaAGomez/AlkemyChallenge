package com.AlkemyCB.SpringJavaJwt.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AlkemyCB.SpringJavaJwt.entity.Characters;
import com.AlkemyCB.SpringJavaJwt.entity.Movie;
import com.AlkemyCB.SpringJavaJwt.model.CharacterModel;
import com.AlkemyCB.SpringJavaJwt.model.ListMovies;
import com.AlkemyCB.SpringJavaJwt.model.MovieModel;




@Component
public class MovieConverter {
	
	@Autowired
	private CharacterConverter characterConverter;
	
	public Movie modelToEntity(MovieModel movieModel) {
		return new Movie(movieModel.getImagen(),movieModel.getTitulo(),movieModel.getFechaCreacion());
	}
	
	public MovieModel entityToModel(Movie movie) {
		return new MovieModel(movie.getImage(),movie.getTitle(),movie.getCreationDate());
	}

	
	
	//veeer
	
	public Movie modelToEntity2(ListMovies movieModel) {
		Set<Characters> listMovie = new HashSet<>();
		for(CharacterModel c: movieModel.getListCharacter()){
			listMovie.add(characterConverter.modelToEntity(c));
		}
		return new Movie(movieModel.getIdMovie(),movieModel.getImagen(), movieModel.getTitulo(), movieModel.getFechaCreacion(),movieModel.getScore(),movieModel.getGender(),
							listMovie
				);

}
	
	public ListMovies entityToModel2(Movie movie) {
		Set<CharacterModel> listCharacter = new HashSet<>();
		for(Characters c: movie.getPersonajesAsociados()){
			listCharacter.add(characterConverter.entityToModel(c));
		}
		return new ListMovies(movie.getIdMovie(), movie.getImage() , movie.getTitle(), movie.getCreationDate(), movie.getScore(),movie.getGender(),listCharacter);

}
	
	
}
