package com.sjy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjy.entity.*;
import com.sjy.service.MicroBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 12 06 12:51
 * @Description: 项目名称 index.html 路径 com.sjy.controller.admin
 * @Function:
 */
@Controller
@RequestMapping("/admin")
public class MicroBlogController {

    @Autowired
    MicroBlogService microBlogService;

    @GetMapping("/microblogs")
    public String getMicroblogs(Model model)
    {

        List<MicroBlog> list = microBlogService.listAllMicroBlog();
        model.addAttribute("microblogs",list);
        return "admin/microblogs";
    }
    @GetMapping("/microblogs/{id}/input")
    public String updateBlog(@PathVariable Long id, Model model)
    {
        MicroBlog blog = microBlogService.getMicroBlogById(id);
        model.addAttribute("microblog",blog);
        return "admin/microblogs-input";
    }

    @GetMapping("/microblogs/{id}/delete")
    public  String deleteBlog(@PathVariable Long id, RedirectAttributes attributes)
    {
        microBlogService.deleteMicroBlog(id);
        attributes.addFlashAttribute("message","刪除成功");
        return "redirect:/admin/microblogs";
    }


    @GetMapping("/microblogs/input")
    public String toUpdate(Model model)
    {

        model.addAttribute("microblog",new MicroBlog());
        return "admin/microblogs-input";
    }
    @PostMapping("/microblogs/{microblogid}")
    public String update(RedirectAttributes attributes,MicroBlog blog)
    {
        blog.setCreateTime(new Date());
        int b=microBlogService.updateMicroBlog(blog);
        if(b==0) attributes.addFlashAttribute("message","修改失败");
        else attributes.addFlashAttribute("message","修改成功");
        return  "redirect:/admin/microblogs";

    }

    @PostMapping("/microblogs")
    public String addNewBlog(RedirectAttributes attributes, MicroBlog blog) throws Exception {
        int b = microBlogService.saveMicroBlog(blog);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/microblogs";

    }


}
