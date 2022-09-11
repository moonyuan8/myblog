package com.sjy.service;

import com.sjy.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 10 16:52
 * @Description: 项目名称 myblog 路径 com.sjy.service
 * @Function:
 */
public interface MessageService {
    //查询留言列表
    List<Message> listMessage();

    //保存留言
    int saveMessage(Message message);

    //查询所有父留言
    List<Message> findParentMessageById(Long id);

    //删除留言
    void deleteMessage(Long id);


}
