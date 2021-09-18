package com.bennetty74.mapper;

import com.bennetty74.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author bennetty74
 * @date 2021.9.12
 */
@Mapper
public interface UserMapper {

    /**
     * select a user by username
     * @param username an unique username in user table
     * @return a user if exist, else return null
     */
    User selectByUsername(String username);

}
