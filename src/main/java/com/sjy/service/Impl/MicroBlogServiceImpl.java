package com.sjy.service.Impl;

import com.sjy.dao.MicroBlogDao;
import com.sjy.entity.Blog;
import com.sjy.entity.MicroBlog;
import com.sjy.service.MicroBlogService;
import com.sjy.util.MarkdownUtils;
import com.sjy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author 傻傻的远
 * @Date 2021 12 06 12:37
 * @Description: 项目名称 index.html 路径 com.sjy.service.Impl
 * @Function:
 */
@Service
public class MicroBlogServiceImpl implements MicroBlogService {
    @Autowired
    MicroBlogDao microBlogDao;

    @Autowired
    RedisUtils redisUtils;
    @Override
    public MicroBlog getMicroBlogById(Long id) {
        return microBlogDao.getMicroBlogById(id);
    }

    @Override
    public List<MicroBlog> listAllMicroBlog() {
        List<MicroBlog>list = new ArrayList<>();
        if(redisUtils.lGet("AllMicroBlog",0,-1).size()!=0)
        {
            for(Object c: Objects.requireNonNull(redisUtils.lGet("AllMicroBlog",0,-1))) {
                list.add((MicroBlog) c);
            }
        }
        else
        {
            list=microBlogDao.listAllMicroBlog();
            for(MicroBlog microBlog: list) {
                redisUtils.lSet("AllMicroBlog",microBlog);
            }
        }
        return list;
    }

    @Override
    public int saveMicroBlog(MicroBlog microBlog) {
        microBlog.setCreateTime(new Date());
//        redisUtils.lSet("AllMicroBlog",microBlog);
        redisUtils.del("AllMicroBlog");
//        redisUtils.del("MicroBlog"+microBlog.getCreateTime().toString().split(" ")[5]);
        redisUtils.leftSet("MicroBlog"+microBlog.getCreateTime().toString().split(" ")[5],microBlog);
        return microBlogDao.saveMicroBlog(microBlog);
    }

    @Override
    public int deleteMicroBlog(Long id) {
        redisUtils.del("AllMicroBlog");
        redisUtils.del("MicroBlog"+microBlogDao.getMicroBlogById(id).getCreateTime().toString().split(" ")[5]);
        return microBlogDao.deleteMicroBlog(id);
    }

    @Override
    public int updateMicroBlog(MicroBlog microBlog) {
        redisUtils.del("AllMicroBlog");
        redisUtils.del("MicroBlog"+microBlog.getCreateTime().toString().split(" ")[5]);
        return microBlogDao.updateMicroBlog(microBlog);
    }

    @Override
    public List<MicroBlog> listAllMicroBlogByYear(Integer year) {
        List<MicroBlog>list = new ArrayList<>();
        if(redisUtils.lGet("MicroBlog"+year,0,-1).size()!=0)
        {
            for(Object c: Objects.requireNonNull(redisUtils.lGet("MicroBlog"+year,0,-1))) {
                list.add((MicroBlog) c);
            }
        }
        else
        {
            list=microBlogDao.listAllMicroBlogByYear(year);
            for(MicroBlog microBlog: list) {
                microBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(microBlog.getContent()));
                redisUtils.lSet("MicroBlog"+year,microBlog);
            }
        }
        return list;
    }
}
