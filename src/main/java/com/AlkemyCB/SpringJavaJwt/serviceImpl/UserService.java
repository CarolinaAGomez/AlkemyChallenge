package com.AlkemyCB.SpringJavaJwt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.AlkemyCB.SpringJavaJwt.entity.Rol;
import com.AlkemyCB.SpringJavaJwt.entity.User;
import com.AlkemyCB.SpringJavaJwt.repository.RoleRepository;
import com.AlkemyCB.SpringJavaJwt.repository.UserRepository;
import com.AlkemyCB.SpringJavaJwt.service.IUserService;

@Service
public class UserService implements IUserService{

	
	@Autowired //inyectar unas dependencias
	private UserRepository userRepo;
	@Autowired 
	private RoleRepository rolRepo;
	@Autowired 
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User userEntity) {
		Rol rol= rolRepo.findByName("ROLE_USER");
		userEntity.setRolUser(rol);
		//encripta la clave
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		
		return userRepo.save(userEntity);
		
	}
	@Override
	public User findByUser(String usuario) {
		return userRepo.findByUser(usuario);
	}
	@Override
	 public User findByLoginAndPassword(String login, String password) {
	        User user = findByUser(login);
	        if (user != null) {
	            if (passwordEncoder.matches(password, user.getPassword())) {
	                return user;
	            }
	        }
	        return null;
	    }
	
}
