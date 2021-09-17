package com.bennetty74.controller;

import com.bennetty74.bean.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bennetty74
 * @date 2021.9.17
 */
@RestController
public class LoginController {

    @PostMapping("login")
    public String login(@RequestBody User user) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        securityContext.setAuthentication(token);
        SecurityContextHolder.setContext(securityContext);
        return "success";
    }

}
