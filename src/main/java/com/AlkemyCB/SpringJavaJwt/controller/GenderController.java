package com.AlkemyCB.SpringJavaJwt.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.AlkemyCB.SpringJavaJwt.entity.Gender;
import com.AlkemyCB.SpringJavaJwt.repository.GenderRepository;

@RestController
@RequestMapping("gender")
public class GenderController {

	@Autowired
	private GenderRepository genderRepository;

	@PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Gender> createGender(@RequestPart("gender") Gender gender,
			@RequestParam(value = "file") MultipartFile image) {

		if (!image.isEmpty()) {

			Path directorioImagen = Paths.get("img"); // Ruta relativa a los archivos cargados en el servidor
			String rutaAbsoluta = directorioImagen.toFile().getAbsolutePath();

			try {
				byte[] bytesImage = image.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + image.getOriginalFilename());
				Files.write(rutaCompleta, bytesImage);
				gender.setImage(image.getOriginalFilename());
			} catch (IOException e) {

				return  ResponseEntity.badRequest().build();
			}
		}

		return new ResponseEntity<Gender>(genderRepository.save(gender), HttpStatus.OK);
	}

}
