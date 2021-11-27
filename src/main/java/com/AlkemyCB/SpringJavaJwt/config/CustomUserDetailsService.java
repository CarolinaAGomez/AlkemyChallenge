package com.AlkemyCB.SpringJavaJwt.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.AlkemyCB.SpringJavaJwt.entity.Rol;
import com.AlkemyCB.SpringJavaJwt.entity.User;
import com.AlkemyCB.SpringJavaJwt.repository.RoleRepository;
import com.AlkemyCB.SpringJavaJwt.serviceImpl.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	
	
	
	
	 @Autowired
	    private UserService userService;
	 
	 
	 @Autowired
	    private RoleRepository roleRepository;
	 
	 
	 @Override
	    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userService.findByUser(username);
	        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
	    }
	
	 @PostConstruct//esta anotación principal que se utilizará en los métodos que se ejecutarán inmediatamente después de que se inicie la aplicación.
	    public void createTestRol() {
	        if (roleRepository.findByName("ROLE_ADMIN") ==null ) {
	            Rol rol = new Rol();
	            	rol.setIdRol(1);
	            	rol.setName("ROLE_ADMIN");
	            	
	                
	            roleRepository.save(rol);
	        }
	        if (roleRepository.findByName("ROLE_USER") ==null ) {
	            Rol rol = new Rol();
	            	rol.setIdRol(2);
	            	rol.setName("ROLE_USER");
	            roleRepository.save(rol);
	        }
	    }

}
