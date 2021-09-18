package com.bennetty74.config;

import com.bennetty74.mapper.RoleMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author Bennetty74
 * @date 2021.9.18
 */
@Component
public class LdapCustomAuthoritiesPopulator implements LdapAuthoritiesPopulator {

    @Resource
    RoleMapper roleMapper;

    /**
     * Get roles from database
     * @param dirContextOperations {@link DirContextOperations}
     * @param username username of login user
     * @return a role list
     */
    @Override
    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations dirContextOperations, String username) {
        return roleMapper.selectUserRoles(username);
    }
}
