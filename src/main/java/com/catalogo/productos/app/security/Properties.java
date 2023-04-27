package com.catalogo.productos.app.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {
	
	@Value("${ACCES_TOKEN_SECRET}")
	private  String ACCES_TOKEN_SECRET;
	@Value("#{new Long('${ACCES_TOKEN_VALIDITY_SECONDS}')}")
	private  Long ACCES_TOKEN_VALIDITY_SECONDS;
	
	
	
	public String getACCES_TOKEN_SECRET() {
		return ACCES_TOKEN_SECRET;
	}

	public Long getACCES_TOKEN_VALIDITY_SECONDS() {
		return ACCES_TOKEN_VALIDITY_SECONDS;
	}

	
}
