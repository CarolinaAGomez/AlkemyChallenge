package com.AlkemyCB.SpringJavaJwt.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.AlkemyCB.SpringJavaJwt.entity.User;

public class CustomUserDetails implements UserDetails {
	
	
	private String login;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
 

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User user)  {
        CustomUserDetails c = new CustomUserDetails();
      //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
        c.login = user.getUser();
        c.password = user.getPassword();
        c.email=user.getEmail();
     // Obtener una authority para autorizar/controlar un acceso. CLASE DE SPRINGBOOT
        c.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRolUser().getName()));
        return c;
    }
    
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
