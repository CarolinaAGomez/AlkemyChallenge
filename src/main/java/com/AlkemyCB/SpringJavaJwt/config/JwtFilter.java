package com.AlkemyCB.SpringJavaJwt.config;

import java.io.IOException;



import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import static org.springframework.util.StringUtils.hasText;
import lombok.extern.java.Log;
@Component
@Log
public class JwtFilter  extends GenericFilterBean{
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	
	public static final String AUTHORIZATION= "Authorization";

	//GENERAR EL TOKEN
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("Do filter");
		String token = getToken((HttpServletRequest) request);
				if (token!=null && jwtProvider.validaToken(token)) {
					String userLogin =jwtProvider.getLoginFromToken(token);
					  CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userLogin);
			            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
			            SecurityContextHolder.getContext().setAuthentication(auth);
				}
				chain.doFilter(request, response);
	}
	
	
	
	
	private String getToken(HttpServletRequest request) {
		String bearer= request.getHeader(AUTHORIZATION);
		if (hasText(bearer) && bearer.startsWith("Bearer ")){
			return bearer.substring(7);	
	}
	return null;
	
	}
}
