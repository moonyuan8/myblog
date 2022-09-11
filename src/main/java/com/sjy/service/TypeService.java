package com.sjy.service;

import com.sjy.entity.Type;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 07 16:13
 * @Description: 项目名称 myblog 路径 com.sjy.service
 * @Function:
 */
public interface TypeService {
    int saveType(Type type);
    Type getType(Long id);

    List<Type> getAllType();
    List<Type> getAllTypeAndBlog();
    Type getTypeByName(String name);
    int updateType(Type type);
    void deleteType(Long id);
}
