package com.catalogo.productos.app.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	
	@Value("${ACCES_TOKEN_SECRET}")
	private  String ACCES_TOKEN_SECRET;
	@Value("${ACCES_TOKEN_VALIDITY_SECONDS}")
	private  Long ACCES_TOKEN_VALIDITY_SECONDS;
	

	

	private  SecretKey hashBase64Bits() {
		 byte[] keyBytes = Decoders.BASE64.decode(ACCES_TOKEN_SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public  String createToken(String nombre,String email,Set<String> roles) {
		long expirationTime = ACCES_TOKEN_VALIDITY_SECONDS * 1_000;
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
	public  UsernamePasswordAuthenticationToken getAuthentication(String token) {
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