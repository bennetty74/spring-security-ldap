package com.bennetty74.bean;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Bennetty74
 * @date 2021.9.18
 */

@Data
public class Role implements GrantedAuthority {

    private Integer id;
    /** role name */
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}
