package com.sjy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjy.entity.Type;
import com.sjy.service.Impl.TypeServiceImpl;
import com.sjy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 07 16:17
 * @Description: 项目名称 myblog 路径 com.sjy.controller
 * @Function:
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    TypeService service;

    @GetMapping("/types")
    public  String getTypes(Model model, @RequestParam(defaultValue = "1",value = "pageNum ")Integer pageNum)
    {
        String order="id desc";
        PageHelper.startPage(pageNum,10,order);
        List<Type> type = service.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(type);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

//    @GetMapping("/types/input")
//    public String inputType()
//    {
//        return "admin/types-input";
//    }
    @GetMapping("/types/input")
    public String inputType(Model model)
    {
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public  String addNew(Type type, RedirectAttributes attributes)
    {
        Type type1=service.getTypeByName(type.getName());
        if(type1!=null)
        {
            attributes.addFlashAttribute("message","不能添加重复的类奥");
            return "redirect:/admin/types/input";
        }
        else {
            int i = service.saveType(type);
            if (i == 0) attributes.addFlashAttribute("message", "新增失败");
            else attributes.addFlashAttribute("message", "添加成功");
            return "redirect:/admin/types";
        }
    }
    @GetMapping("/types/{id}/input")
    public String toedit(@PathVariable long id,Model model)
    {
        model.addAttribute("type",service.getType(id));
        return "admin/types-input";
    }
    @PostMapping("/types/{id}")
    public String edit( Type type,RedirectAttributes attributes)
    {
        Type type1 = service.getTypeByName(type.getName());
        if(type1!=null)
        {
            attributes.addFlashAttribute("message","不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        int t=service.updateType(type);
        if(t==0) attributes.addFlashAttribute("message","修改失败");
        else attributes.addFlashAttribute("message","修改成功");
        return "redirect:/admin/types";
    }

    @GetMapping("types/{id}/delete")
    public  String deleteType(@PathVariable Long id,RedirectAttributes attributes)
    {
        service.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }







}
