package com.itstudy.service.impl;

import com.itstudy.dao.AccountDao;
import com.itstudy.service.AccountService;
import com.itstudy.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private LogService logService;

    @Override
    public void transfer(String out, String in, Double money) {
        try {
            accountDao.outMoney(out, money);
            int i = 1/0; //开启异常
            accountDao.inMoney(in, money);
        } finally {
            logService.log(out, in, money);
        }
    }
}
