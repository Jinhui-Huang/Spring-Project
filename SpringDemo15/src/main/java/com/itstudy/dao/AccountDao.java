package com.itstudy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AccountDao {
    @Update("update account set account_money = account_money + #{money} where account_name = #{name}")
    void inMoney(@Param("name")String name, @Param("money") Double money);

    @Update("update account set account_money = account_money - #{money} where account_name = #{name}")
    void outMoney(@Param("name")String name, @Param("money") Double money);

}
