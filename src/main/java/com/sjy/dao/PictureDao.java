package com.sjy.dao;

import com.sjy.entity.Picture;
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
public interface PictureDao {
    Picture getPictureById(Long id);
    void deletePictureById(Long id);
    int updatePicture(Picture picture);
    int insertPicture(Picture picture);
    List<Picture> listAllPicture();
    List<Picture> listAllPictureAll();
}
