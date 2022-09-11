package com.sjy.controller;

import com.sjy.entity.Friend;
import com.sjy.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 12 09 17:41
 * @Description: 项目名称 index.html 路径 com.sjy.controller.admin
 * @Function:
 */
@Controller
public class FriendShowController {
    @Autowired
    FriendService friendService;

    @GetMapping("/friends")
    public String getFriends(Model model)
    {
        List<Friend> allFriend = friendService.getAllFriend();
        model.addAttribute("friendlinks",allFriend);
        return  "friends";
    }

    @PostMapping("/friends/add")
    public String addFriend(Model model,Friend friend)
    {
        friend.setStatus(0);
        friendService.addFriend(friend);
        List<Friend> allFriend = friendService.getAllFriend();
        model.addAttribute("friendlinks",allFriend);
        model.addAttribute("message","等待确认哦");
        return  "friends";
    }
}
