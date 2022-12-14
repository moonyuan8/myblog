package com.sjy.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sjy.entity.QQUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author 傻傻的远
 * @Date 2022 01 02 18:33
 * @Description: 项目名称 index.html 路径 com.sjy.util
 * @Function:
 */
public class Sendhttp {
    public static QQUser SendGET(String url,String param){
        String result="";//访问返回结果
        BufferedReader read=null;//读取访问结果

        try {
            //创建url
            URL realurl=new URL(url+param);
            //打开连接
            URLConnection connection=realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();

            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //反序列化为qquser对象
        //{"code":200,"msg":"success","data":{"qq":"895474507","name":"愚也愉","avatar":"https://q1.qlogo.cn/g?b=qq&nk=895474507&s=100"}}
        QQUser qqUser = JSON.parseObject(result , new TypeReference<QQUser>() {});

        return qqUser;
    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
