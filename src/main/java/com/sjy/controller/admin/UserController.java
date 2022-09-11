package com.sjy.controller.admin;

import com.sjy.entity.User;
import com.sjy.service.Impl.UserServiceImpl;
import com.sjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author 傻傻的远
 * @Date 2021 11 03 16:34
 * @Description: 项目名称 myblog 路径 com.sjy.controller
 * @Function:
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

//    @GetMapping("/tologin")
//    private String tologin()
//    {
//        return "admin/login";
//    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,RedirectAttributes attributes
                        ) {  // redirect最好用这个参数 model.addAttribute也可以实现
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setNickname(user.getUsername());
            user.setPassword(null);
            session.setAttribute("user",user);
            session.setAttribute("guest",user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";  //model return "/admin/login""
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("user");
        session.removeAttribute("guest");
        return "redirect:/admin";
    }
}
