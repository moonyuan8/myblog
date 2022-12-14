package com.sjy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 傻傻的远
 * @date 2021 11 03 11:46
 * @Description: 项目名称 myblog 路径 com.sjy.po
 * @function:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {
        private Long id;
        private String title;
        private String content;
        private String firstPicture;
        private String flag;
        private Integer views;
        private Integer commentCount;
        private boolean appreciation;
        private boolean shareStatement;
        private boolean commentabled;
        private boolean published;
        private boolean recommend;
        private Date createTime;
        private Date updateTime;
        private Long typeId;
        private Long userId;
        private String description;
        private Type type;
        private User user;
        private List<Comment> comments = new ArrayList<>();
}
