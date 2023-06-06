package com.itstudy;

import com.itstudy.config.SpringConfig;
import com.itstudy.domain.Account;
import com.itstudy.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AppTestByJunit {
    @Autowired
    private AccountService accountService;

    @Test
    public void testFindById() {
        Account account = accountService.findById(18);
        System.out.println(account);
    }

}
