package com.sjy.service.Impl;

import com.sjy.dao.PictureDao;
import com.sjy.entity.Blog;
import com.sjy.entity.Picture;
import com.sjy.service.PictureService;

import com.sjy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 傻傻的远
 * @Date 2021 11 08 16:40
 * @Description: 项目名称 myblog 路径 com.sjy.service.Impl
 * @Function:
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureDao pictureDao;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public Picture getPictureById(Long id) {
        return pictureDao.getPictureById(id);
    }

    @Override
    public void deletePictureById(Long id) {
        redisUtils.del("AllPicture");
        pictureDao.deletePictureById(id);
        listAllPicture();
    }

    @Override
    public int updatePicture(Picture picture) {
        pictureDao.updatePicture(picture);
        redisUtils.del("AllPicture");
        listAllPicture();
        return 1;
    }

    @Override
    public int insertPicture(Picture picture) {
        redisUtils.leftSet("AllPicture",picture);
        return pictureDao.insertPicture(picture);
    }

    @Override
    public List<Picture> listAllPicture() {
        List<Picture>list=new ArrayList<>();
        if(redisUtils.lGet("AllPicture",0,-1).size()!=0)
        {
            for(Object c: Objects.requireNonNull(redisUtils.lGet("AllPicture",0,-1))) {
                list.add((Picture)c);
            }
        }
        else
        {
            list=pictureDao.listAllPicture();
            for(Picture picture : list) {
                redisUtils.lSet("AllPicture",picture);
            }
        }
        return list;
    }

    public List<Picture> listAllPictureAll() {
        return pictureDao.listAllPicture();
    }
}
