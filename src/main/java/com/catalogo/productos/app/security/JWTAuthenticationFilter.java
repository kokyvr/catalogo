package com.catalogo.productos.app.security;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		AuthCredentials authCredentials = new AuthCredentials();

		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getUsername(), authCredentials.getPassword(), Collections.emptyList());
		
		return getAuthenticationManager().authenticate(usernamePAT);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		UserDetailImp userDetail = (UserDetailImp) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetail.getUsername(), userDetail.getNombre(),userDetail.getAuthorities().stream().map(p->p.getAuthority()).collect(Collectors.toSet()));
		
		response.addHeader("Authorization", "Bearer " + token);
		response.getWriter().flush();
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
