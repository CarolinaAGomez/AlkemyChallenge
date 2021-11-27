package com.AlkemyCB.SpringJavaJwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends  WebSecurityConfigurerAdapter {
	

	@Autowired
	private JwtFilter jwtFilter;
	
	
	// CLASE PARA ENCRIPTAR LA PASSWORD
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return  new BCryptPasswordEncoder();
	  }
	  
//CONFIGURACION DE RUTAS
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Se deshabilita las configura por defecto ya que vamos a utilizar JWT
		http.httpBasic().disable()
		.csrf().disable()
		//nunca se creara una sesion http 
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		//SI COLOCO ROLE_ADMIN FALLA CON HAS ROLE
		.antMatchers("/admin/*/").hasRole("ADMIN")
		.antMatchers("/user/**").hasAuthority("ROLE_USER")
		.antMatchers("/auth/register","/auth/login").permitAll()
		.anyRequest().hasAnyRole("ADMIN","USER")
		.and()
		//ANTES DE REALIZAR ALGUNA AUTENTICACION VA AL FILTRO
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	  
	  
	

}
