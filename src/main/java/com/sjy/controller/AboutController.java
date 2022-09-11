package com.sjy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 傻傻的远
 * @Date 2021 11 09 16:05
 * @Description: 项目名称 myblog 路径 com.sjy.controller
 * @Function:
 */
@Controller
public class AboutController {
    @GetMapping("/about")
    public String about()
    {
        return "about";
    }
}
