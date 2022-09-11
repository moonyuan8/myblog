package com.sjy.controller;

import com.sjy.entity.MicroBlog;
import com.sjy.service.MicroBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 12 06 15:14
 * @Description: 项目名称 index.html 路径 com.sjy.controller
 * @Function:
 */
@Controller
public class MicroBlogShowController {
    @Autowired
    MicroBlogService microBlogService;

    @GetMapping("/microblog")
    public String getMicroblogs(Model model)
    {
        //2023再加或者修改成循环
        List<MicroBlog> microBlogs = microBlogService.listAllMicroBlogByYear(2021);
        List<MicroBlog> microBlogs1 = microBlogService.listAllMicroBlogByYear(2022);
        model.addAttribute("microblogs2021",microBlogs);
        model.addAttribute("microblogs2022",microBlogs1);
        return "microblogs";
    }
}
