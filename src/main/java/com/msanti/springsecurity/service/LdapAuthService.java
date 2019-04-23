package com.msanti.springsecurity.service;


import java.util.List;
import java.util.Optional;

import javax.naming.Name;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;

import com.msanti.springsecurity.model.LdapAuthUser;
import com.msanti.springsecurity.model.LdapGrantedAuthority;
import com.msanti.springsecurity.repository.LdapAuthRepository;

@Component
public class LdapAuthService {

	private Logger logger =  LoggerFactory.getLogger(LdapAuthService.class);
	
	@Autowired
	private LdapAuthRepository ldapAuthRepository;
	
	//Create
	public void addUser(LdapAuthUser ldapAuthUser) {
		Name dn = LdapNameBuilder
			      .newInstance()
			      .add("uid", ldapAuthUser.getUserName())
			      .add("ou", "users")
			      .build();

		boolean isExist =  ldapAuthRepository.existsById(dn);
		if(isExist ==false) {
			ldapAuthRepository.save(ldapAuthUser);
		}else {
			logger.info("User with username "+ldapAuthUser.getUserName()+" is already exist ");
		}
	}
	
	//Read
	public LdapAuthUser getUser(String userName) {
		
		Optional<LdapAuthUser> ldapAuthUserOptional = ldapAuthRepository.
					findOne(LdapQueryBuilder.query().where("uid").is(userName));
		if(ldapAuthUserOptional.isPresent()) {
			return ldapAuthUserOptional.get();
		}else {
			return null;
		}
	}
	
	//Update
	public void updateLdapUser(LdapAuthUser ldapUser) {
		ldapAuthRepository.save(ldapUser);
	}
	
	//Delete
	public void deleteUser(String userName) {
		
		Optional<LdapAuthUser> ldapAuthUserOptional = ldapAuthRepository.findOne(LdapQueryBuilder.query().where("uid").is(userName));
		if(ldapAuthUserOptional.isPresent()) {
			ldapAuthRepository.delete(ldapAuthUserOptional.get());
		}else {
			logger.info("User with username "+userName+" does not exist ");
		}
	}

	public void deleteWithTemplate() {
		ldapAuthRepository.deleteFromTemplate(null);
	}
	public void createUserWithLdapTemplate() {
		ldapAuthRepository.create(null);
	}
	
	public boolean authenticateLdapUserWithContext(String userName, String password) {
		return ldapAuthRepository.authenticateLdapUserWithContext(userName, password);
	}
	
	public boolean authenticateLdapUserWithLdapQuery(String userName, String password) {
		return ldapAuthRepository.authenticateLdapUserWithLdapQuery(userName, password);
	}
	
	public List<LdapGrantedAuthority> getUserAuthorities(String userName) {
		return ldapAuthRepository.getUserAuthorities(userName);
	}
}
