package com.catalogo.productos.app.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {
	
	@Value("${ACCES_TOKEN_SECRET}")
	private static String ACCES_TOKEN_SECRET;
	@Value("${ACCES_TOKEN_VALIDITY_SECONDS}")
	private static Long ACCES_TOKEN_VALIDITY_SECONDS;
	public static String getACCES_TOKEN_SECRET() {
		return ACCES_TOKEN_SECRET;
	}
	public static void setACCES_TOKEN_SECRET(String aCCES_TOKEN_SECRET) {
		ACCES_TOKEN_SECRET = aCCES_TOKEN_SECRET;
	}
	public static Long getACCES_TOKEN_VALIDITY_SECONDS() {
		return ACCES_TOKEN_VALIDITY_SECONDS;
	}
	public static void setACCES_TOKEN_VALIDITY_SECONDS(Long aCCES_TOKEN_VALIDITY_SECONDS) {
		ACCES_TOKEN_VALIDITY_SECONDS = aCCES_TOKEN_VALIDITY_SECONDS;
	}
	
	
	
}
