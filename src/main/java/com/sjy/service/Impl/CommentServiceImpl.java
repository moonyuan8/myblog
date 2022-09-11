package com.sjy.service.Impl;

import com.sjy.dao.BlogDao;
import com.sjy.dao.CommentDao;
import com.sjy.entity.Comment;
import com.sjy.service.CommentService;
import com.sjy.util.EmailType;
import com.sjy.util.EmailUtils;
import com.sjy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 08 17:20
 * @Description: 项目名称 myblog 路径 com.sjy.service.Impl
 * @Function:
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Autowired
    BlogDao blogDao;

    @Autowired
    RedisUtils redisUtils;

    List<Comment> tempReply=new ArrayList<>();
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, -1L);
        for(Comment comment:comments)
        {
            Long id=comment.getId();
            String parentNickName=comment.getNickname();
            List<Comment> childComments = commentDao.findByBlogIdParentIdNotNull(blogId, id);
            //查询子评论

            combineChildren(blogId, childComments, parentNickName);
            comment.setReplyComments(tempReply);
            tempReply=new ArrayList<>();
        }
        return comments;
    }

    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickName) {
        if(childComments.size()>0)
        {
            for(Comment comment:childComments)
            {
                String parentName=comment.getNickname();
                comment.setParentNickname(parentNickName);
                tempReply.add(comment);
                Long id=comment.getId();
                recursively(blogId, id, parentName);
            }
        }
    }

    private void recursively(Long blogId, Long id, String parentName) {
        List<Comment> comments=commentDao.findByBlogIdAndReplayId(blogId,id);
        if(comments.size()>0)
        {
            for(Comment comment:comments)
            {
                String parentNickName=comment.getNickname();
                comment.setParentNickname(parentName);
                Long replayId = comment.getId();
                tempReply.add(comment);
                recursively(blogId,replayId,parentNickName);
            }
        }


    }

    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        int num=commentDao.saveComment(comment);
        blogDao.addOne(comment.getBlogId());

        List<Comment>comments=getParentCommentEmailAndContent(comment.getParentCommentId());
        for(Comment com:comments){
            if(com.getEmail()==null||com.getEmail().length()==0) continue;
            try {
                //临时复用字段 用作记录评论博客标题
                com.setParentNickname(blogDao.getBlogById(comment.getBlogId()).getTitle());
                //临时复用字段 用作记录回复内容
                com.setAvatar(comment.getContent());
                EmailUtils.createMail(EmailType.PINGLUN,com);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        redisUtils.incr("comment"+comment.getBlogId(),1);
        redisUtils.incr("blogcommenttotal",1);
        return num;
    }

    @Override
    public List<Comment> getParentCommentEmailAndContent(Long id) {
        Comment parentIdAndEmail = commentDao.findParentIdAndEmail(id);
        List<Comment> res=new ArrayList<>();
        while(parentIdAndEmail!=null){
            res.add(parentIdAndEmail);
            parentIdAndEmail=commentDao.findParentIdAndEmail(parentIdAndEmail.getParentCommentId());
        }
        return res;
    }

    @Override
    public void deleteComment(Comment comment, Long id) {
        commentDao.deleteComment(id);
        blogDao.deleteOne(comment.getBlogId());
        redisUtils.decr("comment"+comment.getBlogId(),1);
        redisUtils.decr("blogcommenttotal",1);
        //删除一层评论后，所有子评论一并删除
        List<Comment> comments = commentDao.findByBlogIdParentIdNotNull(comment.getBlogId(), id);
        if(comments.size()>0)
        {
            for(Comment dcomment:comments) deleteComment(dcomment,dcomment.getId());
        }
    }

    @Override
    public void deleteCommentByBlogId(Long id) {
        commentDao.deleteCommentByBlogId(id);
        redisUtils.del("blogcommenttotal");
    }
}
