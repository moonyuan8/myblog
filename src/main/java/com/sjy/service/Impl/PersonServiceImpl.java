package com.sjy.service.Impl;

import com.sjy.dao.PersonDao;
import com.sjy.entity.Blog;
import com.sjy.entity.Person;
import com.sjy.service.PersonService;
import com.sjy.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.emitter.Emitable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 傻傻的远
 * @Date 2021 11 10 17:53
 * @Description: 项目名称 myblog 路径 com.sjy.service.Impl
 * @Function:
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonDao personDao;

    @Override
    public List<Person> getAllPerson() {

        return personDao.getAllPerson();
    }

    @Override
    public Person getPersonById(Long id) {
        return personDao.getPersonById(id);
    }

    @Override
    public Person getPersonByName(String email) {
        return personDao.getPersonByName(email);
    }

    @Override
    public int addUser(Person user) {
        return personDao.addUser(user);
    }

    @Override
    public String getCode(Long id) {
        return personDao.getCode(id);
    }

    @Override
    public int changeState(Long id) {
        return personDao.changeState(id);
    }

    @Override
    public int changeState(String email) {
        return personDao.changeStateByEmail(email);
    }

    @Override
    public int Byebye(String email) {
        return personDao.Byebye(email);
    }

    @Override
    public String getName(String email) {
        return null;
    }

    @Override
    public int clear() {
        return personDao.clear();
    }

    @Override
    public Long GetIdByEmail(String email) {
        return personDao.GetIdByEmail(email);
    }

    @Override
    public void delete(Long id) {
        personDao.delete(id);
    }
}
