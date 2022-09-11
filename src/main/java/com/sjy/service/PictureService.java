package com.sjy.service;

import com.sjy.entity.Picture;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 08 16:38
 * @Description: 项目名称 myblog 路径 com.sjy.service
 * @Function:
 */

public interface PictureService {
    Picture getPictureById(Long id);
    void deletePictureById(Long id);
    int updatePicture(Picture picture);
    int insertPicture(Picture picture);
    List<Picture> listAllPicture();
    List<Picture> listAllPictureAll();
}
