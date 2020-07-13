package com.vitrine.entities.constants;

public enum EnumToken{
	
	PARAMETERS(60,"*7&14#21KokLol*");
	
	private final long authTokenLifetime;
	private final String secretKey;
	
	private EnumToken(long authTokenLifetime, String secretKey) {
		this.authTokenLifetime = authTokenLifetime;
		this.secretKey = secretKey;
	}

	public long getAuthTokenLifetime() {
		return authTokenLifetime;
	}

	public String getSecretKey() {
		return secretKey;
	}
	
	
}
