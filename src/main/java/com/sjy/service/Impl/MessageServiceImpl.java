package com.sjy.service.Impl;

import com.sjy.dao.MessageDao;
import com.sjy.entity.Blog;
import com.sjy.entity.Comment;
import com.sjy.entity.Message;
import com.sjy.service.MessageService;
import com.sjy.util.EmailType;
import com.sjy.util.EmailUtils;
import com.sjy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author 傻傻的远
 * @Date 2021 11 10 16:52
 * @Description: 项目名称 myblog 路径 com.sjy.service.Impl
 * @Function:
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDao messageDao;

    @Autowired
    RedisUtils redisUtils;
    private List<Message> tempReplys = new ArrayList<>();

    @Override
    public List<Message> listMessage() {

        List<Message>  messages = messageDao.findByParentIdNull(-1L);
        for(Message message : messages)
        {
            Long id = message.getId();
            String parentNickname1 = message.getNickname();
            List<Message> childMessages = messageDao.findByParentIdNotNull(id);
                //查询出子留言
            combineChildren(childMessages, parentNickname1);
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return messages;
    }

    private void combineChildren(List<Message> childMessages, String parentNickname1) {
        //判断是否有一级子回复
        if(childMessages.size() > 0){
            //循环找出子留言的id
            for(Message childMessage : childMessages){
                String parentNickname = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname1);
                tempReplys.add(childMessage);
                Long childId = childMessage.getId();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }
    }

    private void recursively(Long childId, String parentNickname1) {
        //根据子一级留言的id找到子二级留言
        List<Message> replayMessages = messageDao.findByReplayId(childId);

        if(replayMessages.size() > 0){
            for(Message replayMessage : replayMessages){
                String parentNickname = replayMessage.getNickname();
                replayMessage.setParentNickname(parentNickname1);
                Long replayId = replayMessage.getId();
                tempReplys.add(replayMessage);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname);
            }
        }
    }

    @Override
    //存储留言信息
    public int saveMessage(Message message) {
        message.setCreateTime(new Date());
        redisUtils.incr("blogmessagetotal",1);

        List<Message>messages=findParentMessageById(message.getParentMessageId());
        Comment comment=new Comment();
        for(Message mes:messages){
            if(mes.getEmail()==null||mes.getEmail().length()==0) continue;

            try {
                comment.setNickname(mes.getNickname());
                comment.setContent(mes.getContent());
                comment.setEmail(mes.getEmail());
                comment.setAvatar(message.getContent());
                EmailUtils.createMail(EmailType.LIUYAN,comment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return messageDao.saveMessage(message);
    }

    @Override
    public List<Message> findParentMessageById(Long id) {
        Message message = messageDao.findMessageById(id);
        List<Message> res=new ArrayList<>();
        while(message!=null){
            res.add(message);
            message=messageDao.findMessageById(message.getParentMessageId());
        }
        return res;
    }

    //    删除留言
    @Override
    public void deleteMessage(Long id) {
        messageDao.deleteMessage(id);
        List<Message> messages = messageDao.findByParentIdNotNull(id);
        redisUtils.decr("blogmessagetotal",1);
        if(messages.size()>0)
        {
            for(Message message:messages) deleteMessage(message.getId());
        }
    }
}
