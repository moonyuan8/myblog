package com.sjy.service;

import com.sjy.entity.User;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 03 16:26
 * @Description: 项目名称 myblog 路径 com.sjy.service
 * @Function:
 */
public interface UserService {
    User checkUser(String name, String password);
    List<User> getAllUser();
}
