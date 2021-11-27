package com.AlkemyCB.SpringJavaJwt.serviceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AlkemyCB.SpringJavaJwt.converter.CharacterConverter;
import com.AlkemyCB.SpringJavaJwt.entity.Movie;
import com.AlkemyCB.SpringJavaJwt.entity.Characters;
import com.AlkemyCB.SpringJavaJwt.model.CharacterModel;
import com.AlkemyCB.SpringJavaJwt.repository.MovieRepository;
import com.AlkemyCB.SpringJavaJwt.repository.CharacterRepository;
import com.AlkemyCB.SpringJavaJwt.service.ICharacterService;

@Service
public class CharacterService implements ICharacterService {
	
	@Autowired
	private CharacterRepository characterRepository;
	
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CharacterConverter characterConverter;
	
	
	@Override
	public Characters saveOrUpdateCharacter(Characters character) {
		return characterRepository.save(character);
	}
	@Override
	public boolean remove(int idCharacter) {
		try {
			characterRepository.deleteById(idCharacter);
		return true;
		}catch( Exception he){
			return false;
		}
	}
	@Override
	public Optional<Characters>findByIdCharacter(int id) {
		return characterRepository.findById(id);
	}
	@Override
	public List<Characters> findByName(String name) {
		return characterRepository.findByName(name);
	}
	@Override
	public List<Characters> findByAge(int age) {
		return characterRepository.findByAge(age);
	}
	@Override
	public List<Characters> findByWeight(double weight) {
		return characterRepository.findByWeight(weight);
	}
	@Override
	public Movie findByIdMovie(int id) {
		return characterRepository.findByIdMovie(id);
	}
	@Override
	public List<Characters> listDetails(){
		return characterRepository.findAll();
	}
	@Override
		public List<CharacterModel> listCharacter(){
			List<CharacterModel> list= new ArrayList<>();
			
			for (Characters p : characterRepository.findAll()) {
				CharacterModel pe = characterConverter.entityToModel(p);
				list.add(pe);
			}
			return list;
	}
	@Override
		public Characters findByNameAndAge(String name,int age) {
			return characterRepository.findByNameAndAge(name, age);
		}
	@Override
		public Characters findByNameAndPeso(String name,double weigth) {
			return characterRepository.findByNameAndWeight(name,weigth);
		}
	@Override
		public Characters findByNameAndMovie(String name,int id) {
			return characterRepository.findByNameAndMovie(name,id);
		}
	
	
	public void addMovieCharacter(Movie movie, Characters person) {
		System.out.println("PELICULA"+movie);
		System.out.println("personajeee"+person);
		//No funciona
		//person.AddPelicula(peli);
		//person.getPeliculaOserie().add(peli);//con este no funciona
		
		
		movie.addPersonaje(person);
		//peli.getPersonajesAsociados().add(person); //este tiene que estar
		//personajeRepository.save(person);
		movieRepository.save(movie);
	}
	public void deleteMovieCharacter(Movie movie, Characters character) {
		System.out.println("PELICULA"+movie);
		System.out.println("personajeee"+character);
		//No funciona
		//person.AddPelicula(peli);
		character.getMovieOrserie().remove(movie);//con este no funciona
		characterRepository.save(character);
		
		
		//movie.deleteCharacter(character); 
		//movie.getPersonajesAsociados().remove(character);
		
		//movieRepository.save(movie);
	}
		

	
}
