package com.sjy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 傻傻的远
 * @Date 2021 12 06 12:23
 * @Description: 项目名称 index.html 路径 com.sjy.entity
 * @Function:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MicroBlog implements Serializable {
    private Long id;
    private String title;
    private String content;
    private Date createTime;
}
