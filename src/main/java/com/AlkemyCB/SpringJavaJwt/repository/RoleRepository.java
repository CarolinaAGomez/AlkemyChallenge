package com.AlkemyCB.SpringJavaJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AlkemyCB.SpringJavaJwt.entity.Rol;


@Repository
public interface RoleRepository  extends JpaRepository <Rol, Integer>{

	
	 Rol findByName(String name);
	
	
}
