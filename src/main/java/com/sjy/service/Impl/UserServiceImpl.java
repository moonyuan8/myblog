package com.sjy.service.Impl;

import com.sjy.dao.UserDao;
import com.sjy.entity.User;
import com.sjy.service.UserService;
import com.sjy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 03 16:28
 * @Description: 项目名称 myblog 路径 com.sjy.service.Impl
 * @Function:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User checkUser(String name, String password) {
        User user=userDao.findByUsernameAndPassword(name, MD5Utils.code(password));
        if( user==null) return null;
        else return user;
    }


    @Override
    public List<User> getAllUser() {
        List<User> allUser = userDao.getAllUser();
        return  allUser;
    }
}
