package com.sjy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Delete;

import java.io.Serializable;

/**
 * @Author Yuan
 * @Date 2022 09 11 15:05
 * @Description: 项目名称 myblog 路径 com.sjy.entity
 * @Function:
 */

@AllArgsConstructor
@Data
@NoArgsConstructor
public class QQUser implements Serializable {
    private static long SerialVersionUID = 1323424233421L;
    private String code;
    private String msg;
    private QQData data;


    @Data
    public class QQData {
        private String QQ;
        private String Name;
        private String Avatar;
    }
}
