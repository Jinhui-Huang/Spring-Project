package com.itstudy;

import com.itstudy.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForDICollection {
    public static void main(String[] args) {
        //3, 获取IoC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookService = (BookDao) ctx.getBean("bookDao");
        bookService.save();

    }
}
