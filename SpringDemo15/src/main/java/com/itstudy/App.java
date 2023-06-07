package com.itstudy;

import com.itstudy.config.SpringConfig;
import com.itstudy.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //3, 获取IoC容器
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = ctx.getBean(AccountService.class);
        accountService.transfer("Tom", "Jerry", 1000.0);
    }
}
