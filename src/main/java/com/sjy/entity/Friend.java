package com.sjy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 傻傻的远
 * @Date 2021 12 09 17:06
 * @Description: 项目名称 index.html 路径 com.sjy.entity
 * @Function:
 */
public class Friend implements Serializable {
    private Long id;
    private String blogname;
    private String blogaddress;
    private String pictureaddress;
    private Date createtime;
    private String content;
    private int status;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", blogname='" + blogname + '\'' +
                ", blogaddress='" + blogaddress + '\'' +
                ", pictureaddress='" + pictureaddress + '\'' +
                ", createtime=" + createtime +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Friend(Long id, String blogname, String blogaddress, String pictureaddress, Date createtime , int status,String content) {
        this.id = id;
        this.blogname = blogname;
        this.blogaddress = blogaddress;
        this.pictureaddress = pictureaddress;
        this.createtime = createtime;
        this.status=status;
        this.content=content;
    }

    public Friend() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogname() {
        return blogname;
    }

    public void setBlogname(String blogname) {
        this.blogname = blogname;
    }

    public String getBlogaddress() {
        return blogaddress;
    }

    public void setBlogaddress(String blogaddress) {
        this.blogaddress = blogaddress;
    }

    public String getPictureaddress() {
        return pictureaddress;
    }

    public void setPictureaddress(String pictureaddress) {
        this.pictureaddress = pictureaddress;
    }


}
