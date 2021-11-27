package com.AlkemyCB.SpringJavaJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AlkemyCB.SpringJavaJwt.entity.Gender;


@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {
			
	
	public Gender findByName(String name);
	
	
	
}
