package com.AlkemyCB.SpringJavaJwt.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.AlkemyCB.SpringJavaJwt.entity.Gender;


public interface IGenderService {
	
	public Optional<Gender> findById(int id);

}
