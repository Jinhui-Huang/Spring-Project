package com.itstudy.service;

import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    /*
     * 转账操作
     * @param out 传出方
     * @param in 转入方
     * @param money 金额
     * */

    /*
    * 对指定方法的接口方法添加事务@Transactional
    *
    * */
    @Transactional
    void transfer(String out, String in, Double money);
}
