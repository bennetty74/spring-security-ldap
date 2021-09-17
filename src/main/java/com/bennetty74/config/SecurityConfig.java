package com.bennetty74.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.server.UnboundIdContainer;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

/**
 * @author bennetty74
 * @date 2021.9.12
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Specify the encryption method, this example uses {@link BCryptPasswordEncoder} as password encoder
     *
     * @return a specific password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * config the ldap authentication params
     * the port of url is according to {@link #ldapContainer()}
     * which create a {@link UnboundIdContainer} by default port 53389
     *
     * @param auth {@link AuthenticationManagerBuilder}
     * @throws Exception a exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .url("ldap://localhost:53389/dc=springframework,dc=org")
                .and()
                .passwordCompare()
                .passwordEncoder(passwordEncoder())
                .passwordAttribute("userPassword")
                .and()
                .ldapAuthoritiesPopulator(new LdapAuthoritiesPopulator() {
                    @Override
                    public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
                        return Collections.emptyList();
                    }
                });
    }

    /**
     * The configuration does three things:
     * 1. permit path /login without authentication
     * 2. disable csrf(Cross-Site Request Forgery) to permit the cross-site request
     * 3. set all other request should be authenticated before performing these requests
     * @param http the spring security's configuration entity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .and()
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated();
    }

    /**
     * Specify the suffix of the LDAP
     * And specify the LDAP example information for testing, which location is <code>classpath:users.ldif</code>
     *
     * @return a memory container which has the data of LDAP
     */
    @Bean
    UnboundIdContainer ldapContainer() {
        return new UnboundIdContainer("dc=springframework,dc=org",
                "classpath:users.ldif");
    }

}
