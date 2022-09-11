package com.sjy.dao;

import com.sjy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 傻傻的远
 * @date 2021 11 03 12:30
 * @Description: 项目名称 myblog 路径 com.dao
 * @function:
 */
@Mapper
@Repository
public interface UserDao {
    @Select("select * from t_user")
    List<User> getAllUser();

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
