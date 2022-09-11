package com.sjy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjy.dao.TypeDao;
import com.sjy.entity.Blog;
import com.sjy.entity.FirstPageBlog;
import com.sjy.entity.Type;
import com.sjy.service.BlogService;
import com.sjy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 09 15:55
 * @Description: 项目名称 myblog 路径 com.sjy.controller.admin
 * @Function:
 */
@Controller
public class TypeShowController {

    @Autowired
    TypeService service;

    @Autowired
    BlogService blogService;

    @GetMapping("/types/{id}")
    public  String getTypes(Model model ,@PathVariable Long id,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum)
    {
        List<Type> types=new ArrayList<>();
        types.add(new Type(-1L,"全部",blogService.getAllBlog()));
        types.addAll(service.getAllTypeAndBlog());
        model.addAttribute("types",types);
        List<FirstPageBlog> blogs;
        model.addAttribute("activeTypeId", id);
        if(id==-1)   {
            PageHelper.startPage(pageNum,10);
            blogs = blogService.getFirstPageBlog();
            PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogs);
            model.addAttribute("pageInfo",pageInfo);
            return "types-all";
        }
        else  {
            blogs = blogService.getByTypeId(id);
            model.addAttribute("blogs",blogs);
            return "types";
        }

    }
}
