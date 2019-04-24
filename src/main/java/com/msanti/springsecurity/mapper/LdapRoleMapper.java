package com.msanti.springsecurity.mapper;

import javax.naming.NamingException;

import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.security.ldap.userdetails.LdapAuthority;

import com.msanti.springsecurity.model.LdapGrantedAuthority;

public class LdapRoleMapper  implements ContextMapper<LdapGrantedAuthority>{

	@Override
	public LdapGrantedAuthority mapFromContext(Object ctx) throws NamingException {
		DirContextAdapter adapter = (DirContextAdapter) ctx;
		String role = adapter.getStringAttribute("cn");
		LdapGrantedAuthority ldapGranntedAuthority = new LdapGrantedAuthority();
		ldapGranntedAuthority.setAuthority(role);
		return ldapGranntedAuthority;
	}
}