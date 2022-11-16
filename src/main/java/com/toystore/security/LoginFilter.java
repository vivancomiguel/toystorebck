package com.toystore.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toystore.entity.dto.AuthCostumerDto;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	protected LoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		//Obtenemos el body de la petición, que vendrá en formato JSON
		InputStream body = request.getInputStream();
		
		// Asumimos que el Json tiene el formato {"username":"hilario", "password":"solovino"}
		// Realizamos un mapeo de nuestra clase AuthCostumer para obtener datos
		AuthCostumerDto authCostumer = new ObjectMapper().readValue(body,AuthCostumerDto.class);
		
		//Autenticamos el usuario		
		return getAuthenticationManager().authenticate(
			new UsernamePasswordAuthenticationToken(
				authCostumer.getUsername(), 
				authCostumer.getPassword(), 
				Collections.emptyList())
			);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
				//Si la autentificación fue correcta, agregamos el token a la respuesta http
				// tenemos que agregar un método que nos regrese el token
				JwtUtil.addAuthentication(response, authResult.getName());
		
	}



}
