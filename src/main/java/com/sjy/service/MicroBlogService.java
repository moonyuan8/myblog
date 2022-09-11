package com.sjy.service;

import com.sjy.entity.MicroBlog;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 12 06 12:36
 * @Description: 项目名称 index.html 路径 com.sjy.service
 * @Function:
 */

public interface MicroBlogService {
    MicroBlog getMicroBlogById(Long id);
    List<MicroBlog> listAllMicroBlog();
    int saveMicroBlog(MicroBlog microBlog);
    int deleteMicroBlog(Long id);
    int updateMicroBlog(MicroBlog microBlog);
    List<MicroBlog> listAllMicroBlogByYear(Integer year);
}
