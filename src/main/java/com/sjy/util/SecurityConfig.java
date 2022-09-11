//package com.sjy.util;
//
//import com.sjy.entity.User;
//import com.sjy.service.Impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @author 傻傻的远
// * @date 2021 10 12:30
// * @Description:  springsecurity版本
// */
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    UserServiceImpl service;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/*").permitAll()
//                .antMatchers("/admin/**").hasRole("vip");
//
//
//        //没有权限跳到登录界面
//        http.formLogin().loginPage("/tologin");
////        默认保存两周 cache
////        http.rememberMe().rememberMeParameter("rememberMe");
////        http.logout().logoutSuccessUrl("/index");
//    }
//    //如果用springsecurity
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        for (User user : service.getAllUser()) {
//
//            auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                    .withUser(user.getUsername()).password(new BCryptPasswordEncoder().encode(user.getPassword() )).roles("vip");
//            System.out.println(user.getUsername()+"   "+user.getPassword());
//        }
//
//
//
//    }
//}
