package com.itstudy;

import com.itstudy.dao.BookDao;
import com.itstudy.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //3, 获取IoC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        System.out.println(bookDao);
        bookDao.save();
        BookService bookService = ctx.getBean(BookService.class);
        System.out.println(bookService);

    }
}
