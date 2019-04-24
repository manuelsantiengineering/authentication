package com.msanti.springsecurity.model;

import org.springframework.security.core.GrantedAuthority;

public class LdapGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -722234474103517526L;
		
	private String authority;
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}
}
