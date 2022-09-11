package com.sjy.entity;

import java.io.Serializable;

/**
 * @Author 傻傻的远
 * @Date 2021 11 10 17:48
 * @Description: 项目名称 myblog 路径 com.sjy.entity
 * @Function:
 */

public class Person implements Serializable {
    private Long id;
    private String code;
    private String email;
    private String name;
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }


}
