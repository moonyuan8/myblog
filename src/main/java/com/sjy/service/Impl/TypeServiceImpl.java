package com.sjy.service.Impl;

import com.sjy.dao.TypeDao;
import com.sjy.entity.Blog;
import com.sjy.entity.Type;
import com.sjy.service.TypeService;
import com.sjy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 傻傻的远
 * @Date 2021 11 07 16:13
 * @Description: 项目名称 myblog 路径 com.sjy.service.Impl
 * @Function:
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeDao typeDao;
    @Autowired
    RedisUtils redisUtils;


    @Override
    public int saveType(Type type) {
        redisUtils.del("AllType");
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public List<Type> getAllTypeAndBlog() {
        List<Type>list=new ArrayList<>();
        if(redisUtils.lGet("AllType",0,-1).size()!=0)
        {
            for(Object c: Objects.requireNonNull(redisUtils.lGet("AllType",0,-1))) {
                list.add((Type) c);
            }
        }
        else
        {
            list=typeDao.getAllTypeAndBlog();
            for(Type type : list) {
                redisUtils.lSet("AllType",type);
            }
        }
        return list;
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public int updateType(Type type) {
        redisUtils.del("AllType");
        return typeDao.updateType(type);
    }

    @Override
    public void deleteType(Long id) {
        redisUtils.del("AllType");
        typeDao.deleteType(id);
    }
}
