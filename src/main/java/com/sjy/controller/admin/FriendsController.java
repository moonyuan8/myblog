package com.sjy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sjy.entity.Friend;
import com.sjy.entity.Person;
import com.sjy.service.FriendService;
import com.sjy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 10 20:20
 * @Description: 项目名称 myblog 路径 com.sjy.controller.admin
 * @Function:
 */
@Controller
@RequestMapping("/admin")
public class FriendsController {
    @Autowired
    private FriendService friendService;

    @GetMapping("/friends")
    public  String getAllFriends(Model model)
    {
        List<Friend> allPerson = friendService.getAllFriend1();
        model.addAttribute("friends",allPerson);
        return "admin/friends";
    }
    @GetMapping("/friends/{id}/update")
    public String update(@PathVariable Long id,RedirectAttributes attributes)
    {
        attributes.addFlashAttribute("message","更新成功");
        friendService.changeState(id);
        return "redirect:/admin/friends";
    }

    @GetMapping("/friends/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes)
        {
            friendService.deleteById(id);
            attributes.addFlashAttribute("message","删除成功");
            return "redirect:/admin/friends";
        }
}
