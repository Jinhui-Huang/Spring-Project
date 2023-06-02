package com.itstudy.service.impl;

import com.itstudy.dao.BookDao;
import com.itstudy.service.BookService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Component
@Service
@Scope("prototype")
public class BookServiceImpl implements BookService {
    //5, 删除业务层中创建的对象
    private BookDao bookDao;
    @Override
    public void save() {
        System.out.println("book service save...");
        bookDao.save();
    }
    //6, 提供对应的set方法
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
