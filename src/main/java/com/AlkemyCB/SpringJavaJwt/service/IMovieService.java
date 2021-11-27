package com.AlkemyCB.SpringJavaJwt.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.AlkemyCB.SpringJavaJwt.entity.Movie;
import com.AlkemyCB.SpringJavaJwt.model.ListMovies;
import com.AlkemyCB.SpringJavaJwt.model.MovieModel;

public interface IMovieService {
	
	public List<MovieModel> listMovie();
	public Movie saveOrUpdateMovie(Movie pelicula) ;
	public boolean remove(int idPelicula);
	public List<Movie> findByGender(String gender);
	public Movie findByTitle(String title) ;
	public Optional<Movie> findById(int id);
	public Movie findByGenderTitle(int idgenero,String Titulo);
	public List<Movie> findByCreationDateAsc();
	public List<Movie> findByCreationDateDesc();
	public List<ListMovies> listDetails();
	public Movie titleandDateRepeted(String title,LocalDate date);
	
	

}
