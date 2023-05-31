package com.itstudy.service.impl;

import com.itstudy.dao.BookDao;
import com.itstudy.service.BookService;

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
