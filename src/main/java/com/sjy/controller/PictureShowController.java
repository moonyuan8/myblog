package com.sjy.controller;

import com.sjy.dao.PictureDao;
import com.sjy.entity.Picture;
import com.sjy.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 傻傻的远
 * @Date 2021 11 09 15:31
 * @Description: 项目名称 myblog 路径 com.sjy.controller
 * @Function:
 */
@Controller
public class PictureShowController {
    @Autowired
    PictureService pictureService;

    @GetMapping("/picture")
    public String getPicture(Model model)
    {
        model.addAttribute("pictures",pictureService.listAllPicture());
        return "picture";
    }
}
