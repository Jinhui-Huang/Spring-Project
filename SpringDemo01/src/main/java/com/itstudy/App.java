package com.itstudy;

import com.itstudy.dao.BookDao;
import com.itstudy.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //3, 获取IoC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //4, 获取bean
        /*BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();*/

        BookService bookService1 = (BookService) ctx.getBean("bookService");
        BookService bookService2 = (BookService) ctx.getBean("bookService");
        bookService1.save();
        //spring创造出的对象是单例的, 对象只有一个
        System.out.println(bookService1); //@865dd6
        System.out.println(bookService2); //@865dd6

        //通过修改bean的scope属性 scope="prototype" 可以产生不同的对象@4da4253


    }
}
