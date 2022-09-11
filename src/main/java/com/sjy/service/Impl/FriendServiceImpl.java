package com.sjy.service.Impl;

import com.sjy.dao.FriendDao;
import com.sjy.entity.Friend;
import com.sjy.entity.MicroBlog;
import com.sjy.service.FriendService;
import com.sjy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author 傻傻的远
 * @Date 2021 12 09 17:33
 * @Description: 项目名称 index.html 路径 com.sjy.service.Impl
 * @Function:
 */
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    FriendDao friendDao;

    @Autowired
    RedisUtils redisUtils;
    @Override
    public Friend getFriendById(Long id) {
        return friendDao.getFriendById(id);
    }

    @Override
    public Friend getFriendByName(String blogname) {
        return friendDao.getFriendByName(blogname);
    }

    @Override
    public List<Friend> getAllFriend() {
        List<Friend>list = new ArrayList<>();
        if(redisUtils.lGet("AllFriend",0,-1).size()!=0)
        {
            for(Object c: Objects.requireNonNull(redisUtils.lGet("AllFriend",0,-1))) {
                list.add((Friend) c);
            }
        }
        else
        {
            list=friendDao.getAllFriend();
            for(Friend friend: list) {
                redisUtils.lSet("AllFriend",friend);
            }
        }
        return list;
    }

    @Override
    public int addFriend(Friend friend) {
        friend.setCreatetime(new Date());
        return friendDao.addFriend(friend);
    }

    @Override
    public int changeState(Long id) {
        friendDao.changeState(id);
        Friend friend=friendDao.getFriendById(id);
        redisUtils.leftSet("AllFriend",friend);
        return 1;
    }

    @Override
    public int Byebye(String blogname) {
        redisUtils.del("AllFriend");
        return friendDao.Byebye(blogname);
    }

    @Override
    public void deleteById(Long id) {
        redisUtils.del("AllFriend");
        friendDao.deleteById(id);
    }

    @Override
    public Long GetIdByFriend(String blogname) {
        return friendDao.GetIdByFriend(blogname);
    }

    @Override
    public List<Friend> getAllFriend1() {
        return friendDao.getAllFriend1();
    }
}
