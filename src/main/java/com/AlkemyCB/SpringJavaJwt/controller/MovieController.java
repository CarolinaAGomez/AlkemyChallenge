package com.AlkemyCB.SpringJavaJwt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.AlkemyCB.SpringJavaJwt.entity.Gender;
import com.AlkemyCB.SpringJavaJwt.entity.Movie;
import com.AlkemyCB.SpringJavaJwt.model.ListMovies;
import com.AlkemyCB.SpringJavaJwt.model.MovieModel;
import com.AlkemyCB.SpringJavaJwt.repository.GenderRepository;
import com.AlkemyCB.SpringJavaJwt.repository.MovieRepository;
import com.AlkemyCB.SpringJavaJwt.serviceImpl.GenderService;
import com.AlkemyCB.SpringJavaJwt.serviceImpl.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private GenderService genderService;
	
	
	
//LISTA DE PELICULAS
	@GetMapping("")
	public List<MovieModel> listMovie() {
		return movieService.listMovie();
	}
//LISTA CON PERSONAJES ASOCIADOS
	@GetMapping("/listDetails")
	public List<ListMovies> listDetails() {
		return movieService.listDetails();
	}

// CREAR PELICULA
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody Movie movie) {
		
		
		if (movie.getScore() < 1 || movie.getScore() > 5) {

			return new ResponseEntity<>("CALIFICACION DEBERIA SER ENTRE 1 A 5", HttpStatus.NOT_FOUND);

		}
		if(movieService.titleandDateRepeted(movie.getTitle(), movie.getCreationDate())!= null) {
			return new ResponseEntity<>("EL TITULO YA EXISTE CON ESA FECHA", HttpStatus.NOT_ACCEPTABLE);
		}
		Optional<Gender> g = genderService.findById(movie.getGender().getIdGender());
		if (g.isEmpty()) {
			return new ResponseEntity<>("ID " + movie.getGender().getIdGender() + " DE GENERO NO ENCONTRADO",
					HttpStatus.NOT_FOUND);
		}
		
		
		

		return new ResponseEntity<Movie>(movieService.saveOrUpdateMovie(movie), HttpStatus.OK);
	}

	// ACTUALIZAR PELICULA
	// PathVariable con / @requestParam con ?
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody Movie movie, @PathVariable int id) {

		Optional<Movie> mov = movieService.findById(id);

		if (mov.isEmpty()) {
			return new ResponseEntity<>("EL ID " + id + " DE PELICULA NO ENCONTRADO", HttpStatus.NOT_FOUND);
		}
		mov.get().setScore(movie.getScore());
		mov.get().setCreationDate(movie.getCreationDate());
		mov.get().setGender(movie.getGender());
		mov.get().setTitle(movie.getTitle());
		mov.get().setImage(movie.getImage());

		return new ResponseEntity<Movie>(movieService.saveOrUpdateMovie(mov.get()), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePelicula(@PathVariable int id) {
		Optional<Movie> mov = movieService.findById(id);
		if (mov.isEmpty()) {
			return new ResponseEntity<>("EL ID " + id + " DE PELICULA NO ENCONTRADO", HttpStatus.NOT_FOUND);
		}

		movieService.remove(id);

		return new ResponseEntity<>("ELIMINADA OK", HttpStatus.OK);
	}

	// OBJETO FILTRADO POR TITULO Y GENERO
	@GetMapping("/filterTitleGender")
	public Movie findByTitleyGender(@RequestParam(value = "title") String title, @RequestParam(value = "id") int id) {
		Movie listaGender = movieService.findByGenderTitle(id, title);
		return listaGender;
	}

	// LISTADO DE PELICULA OEDENADO POR FECHA ASC
	@GetMapping("/dateAsc")
	public List<Movie> findByCreatiocnDateAsc() {
		List<Movie> findByCreatiocnDateAsc = movieService.findByCreationDateAsc();
		return findByCreatiocnDateAsc;
	}

	// LISTADO DE PELICULA OEDENADO POR FECHA DESCENDENTE
	@GetMapping("/dateDesc")
	public List<Movie> findByCreatiocnDateDesc() {
		List<Movie> findByCreatiocnDateDesc = movieService.findByCreationDateDesc();
		return findByCreatiocnDateDesc;
	}

	@GetMapping("/filterGender")
	public List<Movie> findByGender(@RequestParam(value = "gender") String gender) {
		List<Movie> findByGender = movieService.findByGender(gender);
		return findByGender;
	}

	@GetMapping("/filterTitle")
	public Movie findByTitler(@RequestParam(value = "title") String title) {
		Movie findByTitle = movieService.findByTitle(title);
		return findByTitle;
	}

}
