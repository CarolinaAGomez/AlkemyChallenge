package com.AlkemyCB.SpringJavaJwt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.AlkemyCB.SpringJavaJwt.entity.Movie;
import com.AlkemyCB.SpringJavaJwt.entity.Characters;
import com.AlkemyCB.SpringJavaJwt.model.CharacterModel;
import com.AlkemyCB.SpringJavaJwt.model.Character_Movie;
import com.AlkemyCB.SpringJavaJwt.repository.CharacterRepository;
import com.AlkemyCB.SpringJavaJwt.serviceImpl.MovieService;
import com.AlkemyCB.SpringJavaJwt.serviceImpl.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {

	@Autowired
	public CharacterService personajeService;

	@Autowired
	public MovieService peliculaService;

	@Autowired
	public CharacterRepository personajeRepository;

// LISTADO DE PERSONAJES
	@GetMapping("")
	public List<CharacterModel> listCharacter() {

		return personajeService.listCharacter();
	}

// ALTA PERSONAJE

	@PostMapping(value="/create",consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Characters> create(@RequestPart ("character")Characters character,@RequestParam(value = "file") MultipartFile image) {
		
		if (!image.isEmpty()) {

			Path directorioImagen = Paths.get("img"); // Ruta relativa a los archivos cargados en el servidor
			String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();

			try {
				byte[] bytesImage = image.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + image.getOriginalFilename());
				Files.write(rutaCompleta, bytesImage);
				character.setImage(image.getOriginalFilename());
			} catch (IOException e) {

				return  ResponseEntity.badRequest().build();
			}
		}
		
		Characters p = personajeService.saveOrUpdateCharacter(character);
		return new ResponseEntity<Characters>(p, HttpStatus.OK);
	}

// PARA EDITAR EL PERSONAJE POR ID
	@PutMapping("/update/{id}")
	public ResponseEntity<Characters> update(@RequestBody Characters character, @PathVariable int id) {
		Optional<Characters> p = personajeService.findByIdCharacter(id);

		if (!p.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		p.get().setAge(character.getAge());
		p.get().setHistory(character.getHistory());
		p.get().setName(character.getName());
		p.get().setImage(character.getImage());
		p.get().setWeight(character.getWeight());

		return new ResponseEntity<Characters>(personajeService.saveOrUpdateCharacter(p.get()), HttpStatus.OK);
	}

//ELIMINAR POR ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		Optional<Characters> character = personajeService.findByIdCharacter(id);
		if (!character.isPresent()) {
			return new ResponseEntity<>("No se encuentra el ID " + id + "", HttpStatus.NOT_FOUND);
		}
		personajeService.remove(id);

		return new ResponseEntity<>("Delete", HttpStatus.OK);

	}

// LISTADO DE LOS PERSONAJES CON SUS PELICULAS ASOCIADAS
	@GetMapping("/listDetails")
	public List<Characters> listDetails() {
		return personajeRepository.findAll();
	}

// LISTADO FILTRADO POR NOMBRE
	@GetMapping("/name")
	public List<Characters> findByName(@RequestParam(value = "name") String name) {
		List<Characters> listaName = personajeService.findByName(name);
		return listaName;
	}

// LISTADO FILTRADO POR EDAD
	@GetMapping("/age")
	public List<Characters> findByAge(@RequestParam(value = "age") int age) {
		List<Characters> listAge = personajeService.findByAge(age);
		return listAge;
	}

// LISTADO FILTRADO POR PESO
	@GetMapping("/weight")
	public List<Characters> findByWeight(@RequestParam(value = "weight") int weight) {
		List<Characters> listWeight = personajeService.findByWeight(weight);
		return listWeight;
	}

// LISTADO FILTRADO POR MOVIE
	@GetMapping("/movies")
	public Movie findByMovie(@RequestParam(value = "movies") int idMovie) {
		Movie movie = personajeService.findByIdMovie(idMovie);
		return movie;
	}

// LISTADO FILTRADO NOMBRE Y EDAD
	@GetMapping("/filterAge{name}{age}")
	public ResponseEntity<Characters> findByNameAge(@RequestParam(value = "name") String name,
			@RequestParam(value = "age") int age) {
		Characters findByNameAndAge = personajeService.findByNameAndAge(name, age);
		return new ResponseEntity<Characters>(findByNameAndAge, HttpStatus.OK);
	}

// LISTADO FILTRADO NOMBRE Y PESO
	@GetMapping("/filterWeight{name}{weight}")
	public ResponseEntity<Characters> findByNameWeight(@RequestParam(value = "name") String name,
			@RequestParam(value = "weight") double weight) {
		Characters findByNameAndWeight = personajeService.findByNameAndPeso(name, weight);
		return new ResponseEntity<Characters>(findByNameAndWeight, HttpStatus.OK);
	}

// LISTADO FILTRADO NOMBRE Y PELICULA
	@GetMapping("/filterMovie{name}{movie}")
	public ResponseEntity<Characters> findByNameMovie(@RequestParam(value = "name") String name,
			@RequestParam(value = "movie") int movie) {
		Characters findByNameAndMovie = personajeService.findByNameAndMovie(name, movie);
		return new ResponseEntity<Characters>(findByNameAndMovie, HttpStatus.OK);
	}
	
//ALTA DE RELACION ENTRE PERSONAJE Y PELICULA
	@PostMapping("/addMovieCharacter")
	public ResponseEntity<?> addMovieCharacter(@RequestBody Character_Movie tabla) {

		Characters c = null;
		Movie m = null;

		if (personajeService.findByIdCharacter(tabla.getIdCharacter()).isEmpty()
				|| peliculaService.findById(tabla.getIdMovie()).isEmpty()) {
			return new ResponseEntity<>("NO SE ENCUENTRA EL ID INTRODUCIDO,VERIFIQUE LA EXISTENCIA DE LOS ID INGRESADOS", HttpStatus.OK);
		}
		c = personajeService.findByIdCharacter(tabla.getIdCharacter()).get();
		m = peliculaService.findById(tabla.getIdMovie()).get();
		personajeService.addMovieCharacter(m, c);
		return new ResponseEntity<>("OK", HttpStatus.OK);

	}
	
	
//ELIMINACION DE LA RELACION ENTRE PERSONAJE Y PELICULA
	@DeleteMapping("/deleteMovieCharacter")
	public ResponseEntity<?> deleteMovieCharacter(@RequestBody Character_Movie tabla) {
		Characters c = null;
		Movie m = null;
		

		if (personajeService.findByIdCharacter(tabla.getIdCharacter()).isEmpty()
				|| peliculaService.findById(tabla.getIdMovie()).isEmpty()) {
			return new ResponseEntity<>("NO SE ENCUENTRA EL ID INTRODUCIDO, VERIFIQUE LA EXISTENCIA DE LOS ID INGRESADOS", HttpStatus.OK);
		}
		c = personajeService.findByIdCharacter(tabla.getIdCharacter()).get();
		m = peliculaService.findById(tabla.getIdMovie()).get();
		personajeService.deleteMovieCharacter(m, c);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}
