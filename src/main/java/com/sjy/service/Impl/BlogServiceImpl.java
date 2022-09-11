package com.sjy.service.Impl;

import com.sjy.NotFindException;
import com.sjy.dao.BlogDao;
import com.sjy.dao.CommentDao;
import com.sjy.dao.TypeDao;
import com.sjy.entity.Blog;
import com.sjy.entity.DetailedBlog;
import com.sjy.entity.FirstPageBlog;
import com.sjy.service.BlogService;

import com.sjy.service.CommentService;
import com.sjy.util.MarkdownUtils;
import com.sjy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author 傻傻的远
 * @Date 2021 11 07 20:54
 * @Description: 项目名称 myblog 路径 com.sjy.service.Impl
 * @Function:
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    TypeDao typeDao;


    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }


    @Override
    public List<Blog> listAllBlog() {
        return blogDao.listAllBlog();
    }

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        if(blog.isRecommend())
        {
            redisUtils.del("AllRecommendBlog");
        }
        redisUtils.del("AllType","type"+blog.getTypeId(),"AllFirstPageBlog","newblog","AllBlog");
        redisUtils.incr("blogtotal",1);
        return blogDao.saveBlog(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        redisUtils.del("AllRecommendBlog","AllBlog","AllType","AllFirstPageBlog");
        redisUtils.hdel("detailedBlog","blog"+blog.getId());
        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        redisUtils.del("AllRecommendBlog","AllFirstPageBlog","AllBlog");
        redisUtils.hdel("detailedBlog","blog"+id);
        redisUtils.decr("blogtotal",1);
        commentDao.deleteCommentByBlogId(id);
        redisUtils.del("blogcommenttotal");
        redisUtils.del("AllType","comment"+id,"view"+id);
        return blogDao.deleteBlog(id);
    }


    @Override
    public List<FirstPageBlog> searchBlog(Blog blog) {
        return blogDao.getSearchBlog1(blog);
    }


    @Override
    public List<Blog> getAllBlog() {
        List<Blog>list=new ArrayList<>();
        if(redisUtils.lGet("AllBlog",0,-1).size()!=0)
        {
            for(Object c: Objects.requireNonNull(redisUtils.lGet("AllBlog",0,-1))) {
                list.add((Blog)c);
            }
        }
        else
        {
            list=blogDao.getAllBlog();
            for(Blog blog : list) {
                redisUtils.lSet("AllBlog",blog);
            }
        }
        return list;
    }

//    @Override
//    public List<FirstPageBlog> getFirstPageBlog() {
//        List<FirstPageBlog>list=new ArrayList<>();
//
//        if(redisUtils.lGet("AllFirstPageBlog",0,-1).size()!=0)
//        {
//            for(Object c: Objects.requireNonNull(redisUtils.lGet("AllFirstPageBlog",0,-1))) {
//                FirstPageBlog blog=(FirstPageBlog)c;
//                Long id=blog.getId();
//                Integer commentCount= (Integer) redisUtils.get("comment"+id);
//                Integer viewCount= (Integer) redisUtils.get("view"+id);
//                blog.setCommentCount(commentCount);
//                blog.setViews(viewCount);
//                list.add(blog);
//            }
//        }
//        else
//        {
//            list=blogDao.getFirstPageBlog();
//            for(FirstPageBlog blog : list) {
//                redisUtils.set("view"+blog.getId(),blog.getViews());
//                redisUtils.set("comment"+blog.getId(),blogDao.getCommentCountById(blog.getId()));
//                redisUtils.lSet("AllFirstPageBlog",blog);
//            }
//        }
//        return list;
//    }
    @Override
    public List<FirstPageBlog> getFirstPageBlog()
    {
        return  blogDao.getFirstPageBlog();
    }


    @Override
    public List<Blog> getAllRecommendBlog() {
        List<Blog>list=new ArrayList<>();
        if(redisUtils.lGet("AllRecommendBlog",0,-1).size()!=0)
        {
            for(Object c: Objects.requireNonNull(redisUtils.lGet("AllRecommendBlog",0,-1))) {
                Blog blog= (Blog) c;
                list.add(blog);
            }
        }
        else
        {
            list=blogDao.getAllRecommendBlog();
            for(Blog blog : list) {
                redisUtils.lSet("AllRecommendBlog",blog);
            }
        }
        return list;
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }


    @Override
    public DetailedBlog getDetailedBlog(Long id) {

        DetailedBlog detailedBlog=(DetailedBlog) redisUtils.hget("detailedBlog","blog"+id);
        if(detailedBlog!=null)
        {
            Integer commentCount= (Integer) redisUtils.get("comment"+id);
            Integer viewCount= (Integer) redisUtils.get("view"+id);
            detailedBlog.setCommentCount(commentCount);
            detailedBlog.setViews(viewCount);
            redisUtils.incr("view"+id,1);
            redisUtils.incr("blogviewtotal",1);
            return detailedBlog;
        }
        detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFindException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        redisUtils.hset("detailedBlog","blog"+id,detailedBlog);
        redisUtils.set("view"+id,detailedBlog.getViews()+1);
        redisUtils.incr("blogviewtotal",1);
        redisUtils.set("comment"+id,blogDao.getCommentCountById(id));
        return detailedBlog;
    }

    @Override
    public int updateViews(Long id) {
        return blogDao.updateViews(id);
    }


    @Override
    public int getCommentCountById(Long id) {
        return blogDao.getCommentCountById(id);
    }


    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        List<FirstPageBlog>list=new ArrayList<>();

        if(redisUtils.lGet("Type"+typeId,0,-1).size()!=0)
        {
            for(Object c: Objects.requireNonNull(redisUtils.lGet("type"+typeId,0,-1))) {
                FirstPageBlog blog=(FirstPageBlog)c;
                Long id=blog.getId();
                Integer commentCount= (Integer) redisUtils.get("comment"+id);
                Integer viewCount= (Integer) redisUtils.get("view"+id);
                blog.setCommentCount(commentCount);
                blog.setViews(viewCount);
                list.add(blog);
            }
        }
        else
        {
            list=blogDao.getByTypeId(typeId);
            for(FirstPageBlog blog : list) {
                redisUtils.lSet("type"+typeId,blog);
            }
        }
        return list;
    }

    @Override
    public Integer getBlogTotal() {
        if(redisUtils.hasKey("blogtotal")) return (Integer) redisUtils.get("blogtotal");
        else redisUtils.set("blogtotal",blogDao.getBlogTotal());
        return blogDao.getBlogTotal();
    }


    @Override
    public Integer getBlogViewTotal() {
        if(redisUtils.hasKey("blogviewtotal")) return (Integer) redisUtils.get("blogviewtotal");
        else redisUtils.set("blogviewtotal",blogDao.getBlogViewTotal());
        return blogDao.getBlogViewTotal();
    }

    @Override
    public Integer getBlogCommentTotal() {
        if(redisUtils.hasKey("blogcommenttotal")) return (Integer) redisUtils.get("blogcommenttotal");
        else redisUtils.set("blogcommenttotal",blogDao.getBlogCommentTotal());
        return blogDao.getBlogCommentTotal();
    }

    @Override
    public Integer getBlogMessageTotal() {
        if(redisUtils.hasKey("blogmessagetotal")) return (Integer) redisUtils.get("blogmessagetotal");
        else redisUtils.set("blogmessagetotal",blogDao.getBlogMessageTotal());
        return blogDao.getBlogMessageTotal();
    }

    @Override
    public Integer getLatest() {
        if(redisUtils.hasKey("newblog"))
        {
            return (Integer)redisUtils.get("newblog");
        }
        else
        {
            Integer id=blogDao.getLatest();
            redisUtils.set("newblog",id);
            return id;
        }
    }

    @Override
    public Integer autoAddView() {
        return blogDao.autoAddView();
    }
}
