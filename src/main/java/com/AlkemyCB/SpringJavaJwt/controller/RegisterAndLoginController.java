package com.AlkemyCB.SpringJavaJwt.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AlkemyCB.SpringJavaJwt.config.JwtProvider;
import com.AlkemyCB.SpringJavaJwt.config.MailService;
import com.AlkemyCB.SpringJavaJwt.entity.User;
import com.AlkemyCB.SpringJavaJwt.model.AuthResponse;
import com.AlkemyCB.SpringJavaJwt.model.Login;
import com.AlkemyCB.SpringJavaJwt.repository.UserRepository;
import com.AlkemyCB.SpringJavaJwt.serviceImpl.UserService;

@RestController
@RequestMapping("/auth")
public class RegisterAndLoginController {
	
	  @Autowired
	    private UserService userService;
	    @Autowired
	    private JwtProvider jwtProvider;
	    @Autowired
		MailService mailService;
	    
	    @Autowired
		UserRepository userRepository;;
	
	
	
	@PostMapping("/register")
    public String registerUser(@RequestBody @Validated User user) throws IOException {
        User u = new User();
        
        if (userRepository.findByUser(user.getUser())!=null) {
        	return "EL NOMBRE DE USUARIO YA SE ENCUENTRA EN USO";
        }
        
        u.setPassword(user.getPassword());
        u.setUser(user.getUser());
        u.setEmail(user.getEmail());
        if(!user.getEmail().isEmpty()) {
        	mailService.sendTextEmail(user.getEmail());
        	
        }
        userService.saveUser(u);
        return "OK";
    }

    @PostMapping("/login")
    public AuthResponse auth(@RequestBody Login login) {
    	
        User user = userService.findByLoginAndPassword(login.getUser(), login.getPassword());
        String token = jwtProvider.generarToken(user.getUser());
        return new AuthResponse(token);
    }
	
	
  
	
}
