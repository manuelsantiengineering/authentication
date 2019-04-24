package com.msanti.springsecurity.repository;

import java.util.List;

import com.msanti.springsecurity.model.LdapAuthUser;
import com.msanti.springsecurity.model.LdapGrantedAuthority;


public interface LdapAuthRepositoryCustom  {

	LdapAuthUser findByUserName(String userName);
    List<LdapAuthUser> findByMatchingUserName(String username);
    boolean authenticateLdapUserWithContext(String userName, String password);
    boolean authenticateLdapUserWithLdapQuery(String userName, String password);
    void create(LdapAuthUser ldapAuthUser);
    void deleteFromTemplate(LdapAuthUser ldapAuthUser);
    void createByBindOperation(LdapAuthUser ldapAuthUser);
    void deleteFromTemplateWithUnbind(String userName);
    void updateWithTemplate(LdapAuthUser ldapAuthUser);
    LdapAuthUser findByUid(String uid);
    List<LdapAuthUser> findAllWithTemplate();
    List<LdapAuthUser> findBySurname(String surName);
    List<LdapGrantedAuthority> getUserAuthorities(String userName);
    
}
