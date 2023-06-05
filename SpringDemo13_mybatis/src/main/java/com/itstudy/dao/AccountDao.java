package com.itstudy.dao;


import com.itstudy.domain.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*
* 主要写SQL语句
* */
@Mapper
public interface AccountDao {

    @Delete("delete from emp where id = #{id}")
    void delete(Integer id);

    @Results(id = "reStaff", value = {
            @Result(column = "dept_id", property = "deptId"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from emp")
    List<Account> findAll();

    @Select("select * from emp where id = #{id}")
    @ResultMap("reStaff")
    Account findById(Integer id);

    @Insert("insert into emp(username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "VALUES (#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    @ResultMap("reStaff")
    void insert(Account account);

}
