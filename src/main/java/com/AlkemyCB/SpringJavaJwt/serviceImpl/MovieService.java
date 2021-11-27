package com.AlkemyCB.SpringJavaJwt.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.AlkemyCB.SpringJavaJwt.converter.MovieConverter;
import com.AlkemyCB.SpringJavaJwt.entity.Gender;
import com.AlkemyCB.SpringJavaJwt.entity.Movie;
import com.AlkemyCB.SpringJavaJwt.model.ListMovies;
import com.AlkemyCB.SpringJavaJwt.model.MovieModel;
import com.AlkemyCB.SpringJavaJwt.repository.GenderRepository;
import com.AlkemyCB.SpringJavaJwt.repository.MovieRepository;
import com.AlkemyCB.SpringJavaJwt.service.IMovieService;

@Service
public class MovieService implements IMovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieConverter movieConverter;

	@Autowired
	private GenderRepository genderRepository;

	

//ALTA PELICULA
	@Override
	public Movie saveOrUpdateMovie(Movie movie) {
		// Optional evita que un método devuelva null
		Optional<Gender> gen = genderRepository.findById(movie.getGender().getIdGender());
		movie.setGender(gen.get());
		return movieRepository.save(movie);
	}
//ELIMINACION PELICULA
	@Override
	public boolean remove(int idMovie) {
		try {
			movieRepository.deleteById(idMovie);
			return true;
		} catch (Exception he) {
			return false;
		}
	}
//ENCONTRAR POR ID
	@Override
	public Optional<Movie> findById(int id) {
		return movieRepository.findById(id);
	}
//BUSCAR POR ID DE GENERO
	@Override
	public List<Movie> findByGender(String gender) {
		return movieRepository.findByName(gender);
	}
//BUSCAR POR TITULO
	@Override
	public Movie findByTitle(String title) {
		return movieRepository.findByTitle(title);
	}
//BUSCAR POR TITULO Y ID DE GENERO
	@Override
	public Movie findByGenderTitle(int idgender, String Title) {

		return movieRepository.findByGenderid(idgender, Title);
	}
//ORDENAR FECHA DE CREACION ASCENDENTE
	@Override
	public List<Movie> findByCreationDateAsc() {

		return movieRepository.findByCreationDateAsc();
	}
//ORDENAR FECHA DE CREACION DESCENDENTE
	@Override
	public List<Movie> findByCreationDateDesc() {
		// TODO Auto-generated method stub
		return movieRepository.findByCreationDateDesc();
	}
//LISTADO CON PERSONAJES ASOCIADOS
	public List<ListMovies> listDetails() {
		List<ListMovies> lista = new ArrayList<>();

		for (Movie e : movieRepository.findAll()) {
			ListMovies m = movieConverter.entityToModel2(e);
			lista.add(m);
		}
		return lista;
	}
//LISTADO DE PELICULAS
		@Override
		public List<MovieModel> listMovie() {

			List<MovieModel> movies = new ArrayList<MovieModel>();

			for (Movie mov : movieRepository.findAll()) {

				MovieModel p = movieConverter.entityToModel(mov);
				movies.add(p);
			}
			return movies;
		}

//VER SI EXISTE UNA PELICULA CON EL MISMO TITULO Y FECHA PARA QUE NO SE REPITA
	@Override
	public Movie titleandDateRepeted(String title, LocalDate date) {
		return movieRepository.titleandDateRepeted(title, date);
	}

}
