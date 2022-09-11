package com.sjy.dao;

import com.sjy.entity.Blog;
import com.sjy.entity.DetailedBlog;
import com.sjy.entity.FirstPageBlog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 傻傻的远
 * @date 2021 11 03 12:29
 * @Description: 项目名称 myblog 路径 com.dao
 * @function:
 */
@Repository
@Mapper
public interface BlogDao {
    Blog getBlogById(Long id);
    List<Blog> listAllBlog();
    int saveBlog(Blog blog);
    int updateBlog(Blog blog);
    int deleteBlog(Long id);

    List<FirstPageBlog> getFirstPageBlog();

    List<Blog> getAllRecommendBlog();

    @Select("select * from t_blog")
    List<Blog> getAllBlog();
    List<FirstPageBlog> getSearchBlog(String query);
    List<FirstPageBlog> getSearchBlog1(Blog blog);
    List<Blog> getAllBlog(Blog blog);
    DetailedBlog getDetailedBlog(Long id);
    int updateViews(Long id);
    int getCommentCountById(Long id);
    @Update("update t_blog set comment_count=comment_count-1 where id=#{id}")
    int deleteOne(Long id);
    @Update("update t_blog set comment_count=comment_count+1 where id=#{id}")
    int addOne(Long id);


    List<FirstPageBlog> getByTypeId(Long typeId);
    Integer getBlogTotal();
    Integer getBlogViewTotal();
    Integer getBlogCommentTotal();
    Integer getBlogMessageTotal();


    @Select("select id from t_blog order by create_time desc limit 1")
    Integer getLatest();

    @Update("update t_blog set views = views+3")
    Integer autoAddView();
}
