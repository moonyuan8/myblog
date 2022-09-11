package com.sjy.service;

import com.sjy.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 08 17:19
 * @Description: 项目名称 myblog 路径 com.sjy.service
 * @Function:
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);

//    查询子评论
//    List<Comment> getChildComment(Long blogId,Long id);

    List<Comment> getParentCommentEmailAndContent(Long id);

    //删除评论
    void deleteComment(Comment comment,Long id);

    void deleteCommentByBlogId(Long id);

}
