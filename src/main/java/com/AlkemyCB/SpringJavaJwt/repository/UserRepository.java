package com.AlkemyCB.SpringJavaJwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AlkemyCB.SpringJavaJwt.entity.User;


@Repository
public interface UserRepository  extends JpaRepository <User,Integer>{
	
	//public User saveUser(User userEntity);
	public User findByUser(String user);

}
