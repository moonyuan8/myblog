package com.sjy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjy.entity.Picture;
import com.sjy.service.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 08 16:37
 * @Description: 项目名称 myblog 路径 com.sjy.controller
 * @Function:
 */
@Controller
@RequestMapping("/admin")
public class PictureController {
    @Autowired
    PictureService service;

    @GetMapping("/pictures")
    public String getPictures(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum)
    {
        PageHelper.startPage(pageNum,10);
        List<Picture> pictures = service.listAllPictureAll();
        PageInfo<Picture> pageInfo = new PageInfo<>(pictures);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/pictures";
    }

    @GetMapping("/pictures/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes)
    {
        service.deletePictureById(id);
        attributes.addFlashAttribute("message","删除成功");
        return  "redirect:/admin/pictures";
    }

    @GetMapping("/pictures/input")
    public String toinput(Model model)
    {
        model.addAttribute("picture",new Picture());
        return "admin/pictures-input";
    }

    @PostMapping("/pictures")
    public String input(Picture picture,RedirectAttributes attributes)
    {
        int p=service.insertPicture(picture);
        if(p==0) attributes.addFlashAttribute("message","新增失败");
        else attributes.addFlashAttribute("message","添加成功");
        return "redirect:/admin/pictures";
    }

    @GetMapping("/pictures/{id}/input")
    public String toupdate(@PathVariable Long id,Model model)
    {
        model.addAttribute("picture",service.getPictureById(id));
        return "admin/pictures-input";
    }

    @PostMapping("/pictures/{id}")
    public String update(Picture picture,RedirectAttributes attributes)
    {
        int p=service.updatePicture(picture);
        if(p==0) attributes.addFlashAttribute("message","更新失败");
        else attributes.addFlashAttribute("message","更新成功");
        return  "redirect:/admin/pictures";
    }

}
