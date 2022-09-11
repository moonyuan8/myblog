package com.sjy.util;

/**
 * JavaMail发送邮件: java版本-与web无关
 * 前提是QQ邮箱里帐号设置要开启POP3/SMTP协议
 */
public enum EmailType {
    //关注
    GUANZHU,
    //取消关注
    CANCELGUANZHU,
    //评论
    PINGLUN,
    //留言
    LIUYAN,
    //新增文章
    ADDNEW,

}
