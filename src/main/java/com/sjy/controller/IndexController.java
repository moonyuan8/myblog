package com.sjy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjy.entity.Blog;
import com.sjy.entity.Comment;
import com.sjy.entity.DetailedBlog;
import com.sjy.entity.FirstPageBlog;
import com.sjy.service.BlogService;
import com.sjy.service.CommentService;
import com.sjy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 08 17:16
 * @Description: 项目名称 myblog 路径 com.sjy.controller
 * @Function:
 */
@Controller
public class IndexController {

    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;
    @GetMapping({"/","/index","admin"})
    public String index(Model model)
    {
        List<Blog> recommendBlog = blogService.getAllRecommendBlog();
        for(int i=0;i<3;i++)
        {
            if(recommendBlog.size()>i) model.addAttribute("blog"+i,recommendBlog.get(i));
            else model.addAttribute("blog"+i,null);
        }
        return "index";
    }

    @PostMapping("/search")
    public String search(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum
            ,@RequestParam String query)
    {
        PageHelper.startPage(pageNum,1000);
        List<FirstPageBlog> pageBlogs = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(pageBlogs);
        model.addAttribute("pageInfo",pageInfo);
        return "search";
    }
    @GetMapping("/blogs/latest")
    public String tozuixin()
    {
        Integer id = blogService.getLatest();
        return "redirect:/blog/"+id;
    }

    @GetMapping("/blog/{id}")
    public String tojuti(Model model,@PathVariable Long id)
    {
        DetailedBlog blog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        blogService.updateViews(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog",blog);
        return "blog";

    }

//    博客信息
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        Integer blogTotal = blogService.getBlogTotal();
        Integer blogViewTotal = blogService.getBlogViewTotal();
        Integer blogCommentTotal = blogService.getBlogCommentTotal();
        Integer blogMessageTotal = blogService.getBlogMessageTotal();
        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogMessageTotal",blogMessageTotal);
        return "about :: blogMessage";
    }






}
