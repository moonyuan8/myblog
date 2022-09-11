package com.sjy.service;

import com.sjy.entity.Blog;
import com.sjy.entity.DetailedBlog;
import com.sjy.entity.FirstPageBlog;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 07 20:54
 * @Description: 项目名称 myblog 路径 com.sjy.service
 * @Function:
 */
public interface BlogService {
    Blog getBlogById(Long id);
    List<Blog> listAllBlog();
    int saveBlog(Blog blog);
    int updateBlog(Blog blog);
    int deleteBlog(Long id);
    List<FirstPageBlog> searchBlog(Blog blog);
    List<FirstPageBlog> getFirstPageBlog();


    List<Blog> getAllRecommendBlog();

    List<FirstPageBlog> getSearchBlog(String query);
    List<Blog> getAllBlog();
    DetailedBlog getDetailedBlog(Long id);
    int updateViews(Long id);
    int getCommentCountById(Long id);
    List<FirstPageBlog> getByTypeId(Long typeId);
    Integer getBlogTotal();
    Integer getBlogViewTotal();
    Integer getBlogCommentTotal();
    Integer getBlogMessageTotal();

    Integer getLatest();
    Integer autoAddView();
}
