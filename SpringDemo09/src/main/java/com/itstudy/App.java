package com.itstudy;

import com.itstudy.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
public class App {
    public static void main(String[] args) {
        //1. 加载类路径下的配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2. 绝对路径或者相对路径下的配置文件
        ApplicationContext ctx2 = new FileSystemXmlApplicationContext("SpringDemo09\\src\\main\\resources\\applicationContext.xml");

        //1, 强转类型获取容器
        BookService bookService = (BookService) ctx.getBean("bookService");
        //2, 附加文件类型字节码文件获取bean
        BookService bookService2 = ctx2.getBean("bookService", BookService.class);
        //3, 直接指定文件类型字节码文件获取bean, 局限性在于只能由一个BookService类型的容器
        BookService bookService3 = ctx2.getBean(BookService.class);

        bookService.save();
        bookService2.save();
        bookService3.save();

    }
}

