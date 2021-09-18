package com.bennetty74.controller;

import com.bennetty74.bean.Menu;
import com.bennetty74.bean.User;
import com.bennetty74.service.MenuService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author bennetty74
 * @date 2021.9.17
 */
@RestController
public class LoginController {

    @Resource
    MenuService menuService;

    @PostMapping("login")
    public String login(@RequestBody User user) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        securityContext.setAuthentication(token);
        SecurityContextHolder.setContext(securityContext);
        return "success";
    }

    /**
     * Get menus the user can access by user's role
     * @param authentication {@link Authentication}
     * @return Menu list
     */
    @GetMapping("/success")
    public List<Menu> success(Authentication authentication){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<GrantedAuthority> roleList = new ArrayList<>(authorities);
        return menuService.selectMenuByRoles(roleList);
    }

}
