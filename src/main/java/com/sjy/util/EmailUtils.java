package com.sjy.util;

/**
 * @Author 傻傻的远
 * @Date 2021 11 05 22:28
 * @Description: 项目名称 test 路径 com.sjy.util
 * @Function:邮件发送工具类
 */


import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sjy.dao.BlogDao;
import com.sjy.entity.Comment;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUtils {

    public static void createMail(String acc, String yanzhengma, String jieshou, EmailType type, Comment comment) throws Exception {
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
//        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com password请输入你的邮箱pop3密码
        ts.connect("smtp.qq.com", "sunjyy825", "--------------");
        // 创建邮件
        Message  message = createSimpleMail(acc,session,yanzhengma,jieshou,type);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }


    public static void createMail(EmailType type, Comment comment) throws Exception {
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
//        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，POP3/SMTP协议授权码 163使用：smtp.163.com  密码请输入你的邮箱pop3验证码
        ts.connect("smtp.qq.com", "sunjyy825", "————————————");
        // 创建邮件
        Message  message = createPingLunmail(session,comment,type);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只留言评论通知邮件
     */
    private static Message createPingLunmail(Session session, Comment comment, EmailType type) throws Exception {
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("sunjyy825@qq.com"));
        if(type==EmailType.PINGLUN){
            // 邮件的标题
            message.setSubject("您在moonyuan.top的评论有回复啦");
            String address="https://www.moonyuan.top//blog/"+comment.getBlogId()+"#comment-container";
//            String address="127.0.0.1:8888//blog/"+comment.getBlogId();

            // 邮件的文本内容
            message.setContent("<html lang='zh-CN'><head ><meta charset='utf-8'>"
                            +"</body>亲爱的"+comment.getNickname()+",您在我的博客"+comment.getParentNickname() +"中的评论"+comment.getContent()+"有回复啦"+"</br>"
                            +"回复内容为"+comment.getAvatar()+"</br>"
                            + "<a href ='" + address + "'>点击此处来查看吧</a></body></html>",
                    "text/html;charset=utf-8");
        }else{
            // 邮件的标题
            message.setSubject("您在moonyuan.top的留言有回复啦");
            String address="https://moonyuan.top/message";

            // 邮件的文本内容
            message.setContent("<html lang='zh-CN'><head ><meta charset='utf-8'>"
                            +"</body>亲爱的"+comment.getNickname()+",您在我的个人网站中的留言"+comment.getContent()+"有回复啦"+"</br>"
                            +"回复内容为"+comment.getAvatar()+"</br>"
                            + "<a href ='" + address + "'>点击此处来查看吧</a></body></html>",
                    "text/html;charset=utf-8");
        }
        // 指明邮件的收件人，发件人和收件人如果是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(comment.getEmail()));
        // 返回创建好的邮件对象
        return message;
    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     */
    public static MimeMessage createSimpleMail(String acc,Session session,String yanzhengma, String jieshou,EmailType type) throws Exception {

        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("sunjyy825@qq.com"));
        if(type==EmailType.GUANZHU)
        {
            // 邮件的标题
            message.setSubject("感谢您关注我");
            String address="https://www.moonyuan.top//queren/"+jieshou;
            // 邮件的文本内容
            message.setContent("<html lang='zh-CN'><head ><meta charset='utf-8'>"
                    +"</body>亲爱的"+acc+",感谢您的关注</br>"+"您的验证码为"+yanzhengma + "此验证码今日有效,请勿回复此邮箱</br>"
                    +"您也可以点击链接进行确认"
                    +"<a href='" + address + "'>确认链接</a></body></html>",
                    "text/html;charset=UTF-8");

        }
        else if(type==EmailType.CANCELGUANZHU)
        {
            // 邮件的标题
            message.setSubject("感谢您曾经关注过我");
            // 邮件的文本内容
            message.setContent("<html lang='zh-CN'><head ><meta charset='utf-8'>"
                            +"</body>亲爱的"+acc+",感谢您曾经关注过我，我们后会有期</br>"
                            + "<a href='https://www.moonyuan.top'>有机会再来看看吧</a></body></html>",
                    "text/html;charset=utf-8");


        }
        else if(type==EmailType.ADDNEW)
        {
            // 邮件的标题
            message.setSubject("Yuan的博客更新啦");
            // 邮件的文本内容
            message.setContent("<html lang='zh-CN'><head ><meta charset='utf-8'>"
                            +"</body>亲爱的"+acc+",Yuan的博客更新啦</br>"
                            + "<a href='https://moonyuan.top/blogs/latest'>快点这儿来看看</a></body></html>",
                    "text/html;charset=utf-8");
        }
        // 指明邮件的收件人，发件人和收件人如果是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(jieshou));
        // 返回创建好的邮件对象
        return message;
    }
}