package com.bennetty74.mapper;

import com.bennetty74.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Bennetty74
 * @date 2021.9.18
 */
@Mapper
public interface RoleMapper {

    /**
     * select user's roles by username
     * @param username an unique column
     * @return a {@link Role} list
     */
    List<Role> selectUserRoles(@Param("username") String username);
}
