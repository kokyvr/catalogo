package com.catalogo.productos.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.catalogo.productos.app.service.util.enu.ERoles;

@Configuration
public class WebSecurity {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.authorizeHttpRequests()
				.antMatchers(HttpMethod.GET,"/productos","/categorias").permitAll()
				.antMatchers(HttpMethod.POST,"/productos","/categorias").hasAnyAuthority(ERoles.ROLE_ADMIN.toString(),ERoles.ROLE_MODERADOR.toString())
				.antMatchers(HttpMethod.POST,"/factura").authenticated()
				.and().httpBasic()
				.and().build()
				
				
				;
		
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}