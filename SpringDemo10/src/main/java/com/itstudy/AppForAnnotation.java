package com.itstudy;

import com.itstudy.config.SpringConfig;
import com.itstudy.dao.BookDao;
import com.itstudy.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppForAnnotation {
    public static void main(String[] args) {
        //纯注解文件
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        System.out.println(bookDao);
        bookDao.save();
        ctx.registerShutdownHook();
        //非单例直接在BookServiceImpl类上注解@Scope("prototype")
/*        BookService bookService1 = ctx.getBean(BookService.class);
        BookService bookService2 = ctx.getBean(BookService.class);
        System.out.println(bookService1);
        System.out.println(bookService2);*/

    }
}
