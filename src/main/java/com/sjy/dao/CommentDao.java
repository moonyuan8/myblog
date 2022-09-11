package com.sjy.dao;

import com.sjy.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 傻傻的远
 * @date 2021 11 03 12:29
 * @Description: 项目名称 myblog 路径 com.dao
 * @function:
 */
@Mapper
@Repository
public interface CommentDao {
    //根据创建时间倒序来排
    List<Comment> findByBlogIdParentIdNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);
    //查询一级回复
    List<Comment> findByBlogIdParentIdNotNull(@Param("blogId") Long blogId, @Param("id") Long id);
    //查询二级回复
    List<Comment> findByBlogIdAndReplayId(@Param("blogId") Long blogId,@Param("childId") Long childId);
//    //查询父级对象
//    Comment findByParentCommentId(Long parentCommentId);
    //添加一个评论
    int saveComment(Comment comment);
    //删除评论
    void deleteComment(Long id);

    @Delete("delete from t_comment where blog_id=#{id}")
    void deleteCommentByBlogId(Long id);

    Comment findParentIdAndEmail(Long id);


}
