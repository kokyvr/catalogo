package com.catalogo.productos.app.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	
	private Properties properties;
	
	public TokenUtils() {
		
		this.properties = new Properties();
	}
	

	private   SecretKey hashBase64Bits() {
		 byte[] keyBytes = Decoders.BASE64.decode(properties.getACCES_TOKEN_SECRET());
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public   String createToken(String nombre,String email,Set<String> roles) {
		long expirationTime = properties.getACCES_TOKEN_VALIDITY_SECONDS() * 1_000;
		Date expirationDate = new Date(System.currentTimeMillis()  + expirationTime);
		
		Map<String,Object> extra = new HashMap<>();
		extra.put("nombre", nombre);
		extra.put("roles", roles);
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(hashBase64Bits())
				.compact();
				
	}
	public   UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			Claims claims= Jwts.parserBuilder()
					.setSigningKey(hashBase64Bits())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
			String email = claims.getSubject();
			@SuppressWarnings("unchecked")
			List<String> roles = claims.get("roles", List.class);
			return new UsernamePasswordAuthenticationToken(email, null,roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		} catch (JwtException e) {
			return null;
		}
	}
	
	
}