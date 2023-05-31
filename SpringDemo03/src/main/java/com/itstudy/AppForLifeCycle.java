package com.itstudy;

import com.itstudy.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForLifeCycle {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //关闭钩子声明关闭虚拟机前关闭容器
        ctx.registerShutdownHook();

        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
        //直接关闭容器
        ctx.close();

    }
}
