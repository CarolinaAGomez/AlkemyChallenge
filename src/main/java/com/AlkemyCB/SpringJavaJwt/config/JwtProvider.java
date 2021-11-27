package com.AlkemyCB.SpringJavaJwt.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;




@Component
@Log
public class JwtProvider {
	
	@Value("$(jwt.secret)")
	private String jwtSecret;
	
	public String generarToken(String usuario) {
		//para que se convierta en date
		Date fechaExpiracion= Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant());
		//Se obtiene de la libreria io.jsonwebtoken
		return Jwts.builder()
				.setSubject(usuario)
				.setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public boolean validaToken(String token) {
	try {
	Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
	return true;
	}catch(Exception e) {
		System.out.println("Token invalido"+e.getMessage());
			
	}
	return false;
	}
	
	
	public String getLoginFromToken(String token) {
		Claims claims= Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	
}
