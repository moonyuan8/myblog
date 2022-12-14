package com.sjy.controller;


import com.sjy.entity.Blog;
import com.sjy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Description: 时间轴页面显示控制器
 * @Date: Created in 9:54 2020/4/16
 * @Author: Yuan
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        List<Blog> blogs = blogService.getAllBlog();
        model.addAttribute("blogs", blogs);
        return "archives";
    }

}