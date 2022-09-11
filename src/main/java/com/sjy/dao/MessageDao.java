package com.sjy.dao;

import com.sjy.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 傻傻的远
 * @date 2021 11 03 12:30
 * @Description: 项目名称 myblog 路径 com.dao
 * @function:
 */
@Mapper
@Repository
public interface MessageDao {
    //添加一个评论
    int saveMessage(Message message);

    //查询父级评论
    List<Message> findByParentIdNull(@Param("ParentId") Long ParentId);

    //查询一级回复
    List<Message> findByParentIdNotNull(@Param("id") Long id);

    //查询二级以及所有子集回复
    List<Message> findByReplayId(@Param("childId") Long childId);

    //查询指定评论
    Message findMessageById(Long id);


    //删除评论
    void deleteMessage(Long id);
}
