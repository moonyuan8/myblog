package com.sjy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 分类实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();


}