package com.sjy.dao;

import com.sjy.entity.MicroBlog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 12 06 12:28
 * @Description: 项目名称 index.html 路径 com.sjy.dao
 * @Function:
 */
@Repository
@Mapper
public interface MicroBlogDao {
    @Select("select * from t_microblog where id=#{id}")
    MicroBlog getMicroBlogById(Long id);

    @Select("select * from t_microblog order by create_time desc")
    List<MicroBlog> listAllMicroBlog();

    @Select("select * from t_microblog where year(create_time)=#{year} order by create_time desc")
    List<MicroBlog> listAllMicroBlogByYear(Integer year);
    @Insert("insert into t_microblog (id,content,title,create_time) values(#{id},#{content},#{title},#{createTime})")
    int saveMicroBlog(MicroBlog microBlog);
    @Delete("delete from t_microblog where id=#{id}")
    int deleteMicroBlog(Long id);
    @Update("update t_microblog set content=#{content},title=#{title} where id=#{id}")
    int updateMicroBlog(MicroBlog microBlog);
}
