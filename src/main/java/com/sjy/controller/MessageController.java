package com.sjy.controller;

import com.sjy.entity.Message;
import com.sjy.entity.QQUser;
import com.sjy.entity.User;
import com.sjy.service.MessageService;
import com.sjy.util.Sendhttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 10 16:51
 * @Description: 项目名称 myblog 路径 com.sjy.controller
 * @Function:
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Value("${message.avatar}")
    private String avatar;

    @GetMapping("/message")
    public String message() {
        return "message";
    }

    //    查询留言
    @GetMapping("/messagecomment")
    public String messages(Model model,HttpSession session) {
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message::messageList";
    }

    //    新增留言
    @PostMapping("/message")
    public String post(Message message, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        User guest = (User) session.getAttribute("guest");
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        }
        else if(guest!=null)
        {
            message.setAvatar(guest.getAvatar());
            message.setNickname(guest.getNickname());
        }
        //设置头像
        else {
            String qq=message.getNickname();
            String email=message.getEmail();
            String[] em=email.split("@");
            if("qq.com".equals(em[1])) qq=em[0];
            if(Sendhttp.isInteger(qq)&&qq.length()>=6&&qq.length()<=12)
            {
                QQUser qqUser = Sendhttp.SendGET("https://api.usuuu.com/qq/", qq);
                String avater1=qqUser.getData().getAvatar();
                String nickname=qqUser.getData().getName();
                message.setAvatar(avater1);
                message.setNickname(nickname);
                User user1 = new User();
                user1.setNickname(nickname);
                user1.setAvatar(avater1);
                user1.setEmail(email);
                session.setAttribute("guest",user1);
            }
            else message.setAvatar(avatar);
        }
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        messageService.saveMessage(message);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message::messageList";
    }

    //    删除留言
    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes, Model model){
        messageService.deleteMessage(id);
        return "redirect:/message";
    }

}
