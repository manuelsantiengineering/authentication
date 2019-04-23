package com.msanti.springsecurity.repository;


import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.msanti.springsecurity.model.LdapAuthUser;



@Repository
public interface LdapAuthRepository extends LdapRepository<LdapAuthUser>,LdapAuthRepositoryCustom{

}
