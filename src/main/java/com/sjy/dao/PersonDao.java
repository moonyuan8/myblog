package com.sjy.dao;


import com.sjy.entity.Person;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author 傻傻的远
 * @Date 2021 11 04 13:12
 * @Description: 项目名称 test 路径 com.sjy.dao
 * @Function:
 */
@Repository
@Mapper
public interface PersonDao {

    @Select("select * from t_person where id=#{id}")
    Person getPersonById(Long id);

    @Select("select * from t_person where email=#{email}")
    Person getPersonByName(String email);

    @Select("select * from t_person ")
    List<Person> getAllPerson();




    @Insert("insert into t_person (email,name,status,code) values(#{email},#{name},#{status},#{code})")
    int addUser(Person user);

    @Select("select code from t_person where id=#{id}")
    String getCode(Long id);

    @Update("update t_person set status=1 where id=#{id}")
    int changeState(Long id);

    @Update("update t_person set status=1 where email=#{email}")
    int changeStateByEmail(String email);

    @Delete("delete from t_person where email=#{email}")
    int Byebye(String email);




    @Select("select name from t_person where email=#{email}")
    String getName(String email);
    @Delete("delete from t_person where status=0")
    int clear();

    @Delete("delete from t_person where id=#{id}")
    void delete(Long id);

    @Select("select id from t_person where email=#{email}")
    Long GetIdByEmail(String email);

}
