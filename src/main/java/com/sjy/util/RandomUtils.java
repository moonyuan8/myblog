package com.sjy.util;

import java.util.Random;

/**
 * @Author 傻傻的远
 * @Date 2021 11 06 13:25
 * @Description: 项目名称 test 路径 com.sjy.util
 * @Function:
 */
public class RandomUtils {
    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i ++) {
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        return sb.toString();
    }


}
