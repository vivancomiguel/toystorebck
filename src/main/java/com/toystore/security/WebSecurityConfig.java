package com.toystore.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@SuppressWarnings("deprecation")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/costumer/**").permitAll()
				//.antMatchers(HttpMethod.POST, "/api/costumer").hasRole("USER")
				//.antMatchers(HttpMethod.PUT, "/api/costumer").hasRole("USER")
				//.antMatchers(HttpMethod.DELETE, "/api/costumer/**").hasRole("ADMIN")
			.anyRequest().authenticated() //.permitAll()
			.and()
			.addFilterBefore(new LoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class) 
			// Actuara este filtro sobre login y esto nos dara el token JWT
			.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class) // Revisara si el token es correcto
			.csrf().disable();							
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("hilario")
			.password("{noop}solovino")
			.roles("ADMIN")
			.and()
			.withUser("chispa")
			.password("{noop}suprema")
			.roles("USER");	
	}
}
