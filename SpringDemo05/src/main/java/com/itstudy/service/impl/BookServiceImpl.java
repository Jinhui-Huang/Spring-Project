package com.itstudy.service.impl;

import com.itstudy.dao.BookDao;
import com.itstudy.dao.UserDao;
import com.itstudy.service.BookService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BookServiceImpl implements BookService {

    private BookDao bookDao;
    private UserDao userDao;

    public BookServiceImpl(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("book service save...");
        bookDao.save();
        userDao.save();
    }
}
