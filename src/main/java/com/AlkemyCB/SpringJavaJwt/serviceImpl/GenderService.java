package com.AlkemyCB.SpringJavaJwt.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.AlkemyCB.SpringJavaJwt.entity.Gender;
import com.AlkemyCB.SpringJavaJwt.repository.GenderRepository;
import com.AlkemyCB.SpringJavaJwt.service.IGenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GenderService implements IGenderService{

	
	@Autowired
	private GenderRepository genderRepository;
	
	public Optional<Gender> findById(int id){
		return genderRepository.findById(id);
	}
	
	
	public Gender save (Gender gender) {
		
		  return genderRepository.save(gender);
		
	}
}
