package com.sjy.controller;

import com.sjy.entity.Comment;
import com.sjy.entity.DetailedBlog;
import com.sjy.entity.QQUser;
import com.sjy.entity.User;
import com.sjy.service.BlogService;
import com.sjy.service.CommentService;
import com.sjy.util.RedisUtils;
import com.sjy.util.Sendhttp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date 2021 11 09 18:01
 * @Description: 项目名称 myblog 路径 com.sjy.controller
 * @Function:
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    //    查询评论列表
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    //    新增评论
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session, Model model) {
        Long blogId = comment.getBlogId();
        User user = (User) session.getAttribute("user");
        User guest = (User) session.getAttribute("guest");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }
        else if(guest!=null)
        {
            comment.setAvatar(guest.getAvatar());
            comment.setNickname(guest.getNickname());
        }
        else {
            String qq=comment.getNickname();
            String email=comment.getEmail();
            String[] em=email.split("@");
            if("qq.com".equals(em[1])) qq=em[0];
            if(Sendhttp.isInteger(qq)&&qq.length()>=6&&qq.length()<=12)
            {
                //逻辑上不应当写在controller层 后序优化到service层

                QQUser qqUser = Sendhttp.SendGET("https://api.usuuu.com/qq/", qq);
                String avater1=qqUser.getData().getAvatar();
                String nickname=qqUser.getData().getName();
                comment.setAvatar(avater1);
                comment.setNickname(nickname);
                User user1 = new User();
                user1.setNickname(nickname);
                user1.setAvatar(avater1);
                user1.setEmail(email);
                session.setAttribute("guest",user1);
            }
            else comment.setAvatar(avatar);
        }

        if (comment.getParentComment().getId() != null) {
            comment.setParentCommentId(comment.getParentComment().getId());
        }
        commentService.saveComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }

    //    删除评论
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id, Comment comment, Model model){
        commentService.deleteComment(comment,id);
        DetailedBlog detailedBlog = blogService.getDetailedBlog(blogId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);

        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }

}
