package com.bennetty74.mapper;

import com.bennetty74.bean.Menu;
import com.bennetty74.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author Bennetty74
 * @date 2021.9.18
 */
@Mapper
public interface MenuMapper {

    /**
     * select menus by having roles
     * @param roleList {@link Role} list
     * @return {@link Menu} list
     */
    List<Menu> selectMenusByRoles(List<? extends GrantedAuthority> roleList);
}
