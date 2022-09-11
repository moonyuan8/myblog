package com.sjy.dao;

import com.sjy.entity.Friend;
import com.sjy.entity.Person;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 12 09 17:08
 * @Description: 项目名称 index.html 路径 com.sjy.dao
 * @Function:
 */
@Mapper
@Repository
public interface FriendDao {
    @Select("select * from t_friend where id=#{id}")
    Friend getFriendById(Long id);

    @Select("select * from t_friend where blogname=#{blogname}")
    Friend getFriendByName(String blogname);

    @Select("select * from t_friend where status=1  order by createtime desc ")
    List<Friend> getAllFriend();

    @Select("select * from t_friend order by createtime desc ")
    List<Friend> getAllFriend1();

    @Insert("insert into t_friend (blogname,blogaddress,pictureaddress,status,content,createtime) values(#{blogname},#{blogaddress},#{pictureaddress},#{status},#{content},#{createtime})")
    int addFriend(Friend friend);


    @Update("update t_friend set status=1 where id=#{id}")
    int changeState(Long id);

    @Delete("delete from t_friend where blogname=#{blogname}")
    int Byebye(String blogname);


    @Delete("delete from t_friend where id=#{id}")
    void deleteById(Long id);

    @Select("select id from t_friend where blogname=#{blogname}")
    Long GetIdByFriend(String blogname);

}
