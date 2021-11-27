package com.AlkemyCB.SpringJavaJwt.service;


import com.AlkemyCB.SpringJavaJwt.entity.User;

public interface IUserService {
	

	public User saveUser(User userEntity);
		
	public User findByUser(String user) ;
	
	public User findByLoginAndPassword(String login, String password);

}
