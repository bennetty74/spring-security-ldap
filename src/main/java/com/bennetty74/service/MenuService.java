package com.bennetty74.service;

import com.bennetty74.bean.Menu;
import com.bennetty74.bean.Role;
import com.bennetty74.mapper.MenuMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Bennetty74
 * @date 2021.9.18
 */
@Service
public class MenuService {
    @Resource
    MenuMapper menuMapper;

    public List<Menu> selectMenuByRoles(List<? extends GrantedAuthority> roleList){
        List<Menu> menus = menuMapper.selectMenusByRoles(roleList);
        // todo sort menus
        return menus;
    }
}
