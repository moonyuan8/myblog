package com.sjy.service;

import com.sjy.entity.Person;

import java.util.List;


/**
 * @Author 傻傻的远
 * @Date 2021 11 10 17:52
 * @Description: 项目名称 myblog 路径 com.sjy.service
 * @Function:
 */
public interface PersonService {
    List<Person> getAllPerson();
    Person getPersonById(Long id);
    Person getPersonByName(String email);
    int addUser(Person user);
    String getCode(Long id);
    int changeState(Long id);
    int changeState(String email);
    int Byebye(String email);
    String getName(String email);
    int clear();
    Long GetIdByEmail(String email);
    void delete(Long id);
}
