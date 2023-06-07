package com.itstudy.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogDao {

    @Insert("insert into account_logs(account_info, crate_date) values (#{info}, now())")
    void log(String info);
}
