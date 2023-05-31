package com.itstudy;

import com.itstudy.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForDIConstructor {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) ctx.getBean("bookService");
        bookService.save();


    }
}
