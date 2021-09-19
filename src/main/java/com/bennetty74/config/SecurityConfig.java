package com.bennetty74.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.server.UnboundIdContainer;

import javax.annotation.Resource;

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

    @Resource
    LdapCustomAuthoritiesPopulator ldapCustomAuthoritiesPopulator;

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
                .ldapAuthoritiesPopulator(ldapCustomAuthoritiesPopulator);
    }

    /**
     * The configuration does five things:
     * 1. disable csrf(Cross-Site Request Forgery) to permit the cross-site request
     * 2. permit accessing path <code>/login</code> without authentication
     * 3. config that access path like <code>/admin/**</code> with <code>ROLE_ADMIN</code> authority
     * 4. config that access path like <code>/user/**</code> with <code>ROLE_USER</code> authority
     * 5. set all other request should be authenticated before performing these requests
     * @param http the spring security's configuration entity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .anyRequest().authenticated();
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
