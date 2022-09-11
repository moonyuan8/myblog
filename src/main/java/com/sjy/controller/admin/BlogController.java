package com.sjy.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjy.entity.*;
import com.sjy.service.BlogService;
import com.sjy.service.PersonService;
import com.sjy.service.TypeService;
import com.sjy.util.EmailType;
import com.sjy.util.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * @Author 傻傻的远
 * @Date 2021 11 04 20:18
 * @Description: 项目名称 myblog 路径 com.sjy.controller
 * @Function:
 */

@Controller
@RequestMapping("/admin")
public class BlogController{

    @Autowired
    BlogService blogService;
    @Autowired
    PersonService personService;

    @Autowired
    TypeService typeService;

    @GetMapping("/blogs")
    public  String listBlog(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum)
    {
        String order="create_time desc";
        PageHelper.startPage(pageNum,10,order);
        List<Blog> list = blogService.listAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }
    @GetMapping("/blogs/{id}/input")
    public String updateBlog(@PathVariable Long id,Model model)
    {
        Blog blog = blogService.getBlogById(id);
        List<Type> types = typeService.getAllType();
        model.addAttribute("types",types);
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/delete")
    public  String deleteBlog(@PathVariable Long id, RedirectAttributes attributes)
    {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","刪除成功");
        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/input")
    public String toUpdate(Model model)
    {

        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.getAllType());
        return "admin/blogs-input";
    }
    @PostMapping("/blogs/{blogid}")
    public String update(RedirectAttributes attributes,Blog blog)
    {
        int b=blogService.updateBlog(blog);
        if(b==0) attributes.addFlashAttribute("message","修改失败");
        else attributes.addFlashAttribute("message","修改成功");
        return  "redirect:/admin/blogs";

    }

    @PostMapping("/blogs")
    public String addNewBlog(RedirectAttributes attributes, Blog blog, HttpSession session) throws Exception {
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        int b = blogService.saveBlog(blog);

        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            List<Person> allPerson = personService.getAllPerson();
            if(allPerson.size()>0)
            {
                for(Person person:allPerson)
                {
                    EmailUtils.createMail(person.getName(),"",person.getEmail(), EmailType.ADDNEW,null);
                }
            }

            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";

    }

    @PostMapping("/blogs/search")
    public String search(Blog searchBlog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<FirstPageBlog> blogBySearch = blogService.searchBlog(searchBlog) ;
        PageHelper.startPage(pageNum, 10);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blog",blogBySearch);
        return "admin/blogs";
    }

}
