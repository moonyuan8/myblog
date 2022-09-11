package com.sjy.service;

import com.sjy.entity.Friend;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 12 09 17:30
 * @Description: 项目名称 index.html 路径 com.sjy.service
 * @Function:
 */

public interface FriendService {
    Friend getFriendById(Long id);
    Friend getFriendByName(String blogname);
    List<Friend> getAllFriend();

    int addFriend(Friend friend);
    int changeState(Long id);
    int Byebye(String blogname);
    void deleteById(Long id);
    Long GetIdByFriend(String blogname);

    List<Friend> getAllFriend1();
}
