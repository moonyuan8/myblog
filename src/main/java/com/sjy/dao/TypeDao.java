package com.sjy.dao;

import com.sjy.entity.Type;
import org.apache.ibatis.annotations.Mapper;
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
public interface TypeDao {
    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getAllTypeAndBlog();

    Type getTypeByName(String name);

    int updateType(Type type);

    void deleteType(Long id);

}
