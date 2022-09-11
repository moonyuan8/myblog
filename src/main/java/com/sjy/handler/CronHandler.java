package com.sjy.handler;

import com.sjy.dao.PersonDao;
import com.sjy.entity.Blog;
import com.sjy.entity.Type;
import com.sjy.service.*;
import com.sjy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Author 傻傻的远
 * @Date 2021 12 05 15:23
 * @Description: 项目名称 index.html 路径 com.sjy.handler
 * @Function:
 */
@Service
//定时清除数据库中状态为0的邮箱
public class CronHandler {
    @Autowired
    PersonService personService;

    @Autowired
    BlogService blogService;

    @Autowired
    RedisUtils redisUtils;

//    @Autowired
//    BlogService blogService;
//    @Autowired
//    TypeService typeService;
//    @Autowired
//    MicroBlogService microBlogService;
//    @Autowired
//    RedisUtils redisUtils;
    //秒 分 时 日 月 星期几  cron表达式
//    @Scheduled(cron = "0 0 0 * * ?")//每天零点执行一次
//    @Scheduled(cron = "30 0/5 19,10 * * ?")  //每天 10点 19点每隔五分钟的30秒执行
//    @Scheduled(cron = "30 47 19 * * ?")  //每天 19:47:30执行
//    @Scheduled(cron = "0/30 * * * * ?")  //每30秒执行一次
//    public void autoadd()
//    {
//        personService.clear();
//        blogService.autoAddView();
//        for (Blog blog : blogService.getAllBlog()) {
//            Long id=blog.getId();
//            redisUtils.incr("view"+id,3);
//        }
//        redisUtils.incr("blogviewtotal",3*blogService.getBlogTotal());
//    }

//    @Scheduled(cron="0 0 4 ? * 4")//每周三四点执行一次
//    public void clearCache()
//    {
//        redisUtils.clear();
//        microBlogService.listAllMicroBlog();
//        microBlogService.listAllMicroBlogByYear(2021);
//        microBlogService.listAllMicroBlogByYear(2022);
//        for (Blog blog : blogService.getAllBlog()) {
//            blogService.getDetailedBlog(blog.getId());
//        }
//
//        blogService.getAllRecommendBlog();
//        blogService.getFirstPageBlog();
//        blogService.getBlogCommentTotal();
//        blogService.getBlogMessageTotal();
//        blogService.getBlogTotal();
//        blogService.getLatest();
//        typeService.getAllType();
//        for (Type type : typeService.getAllTypeAndBlog()) {
//            blogService.getByTypeId(type.getId());
//        }
//    }
}
