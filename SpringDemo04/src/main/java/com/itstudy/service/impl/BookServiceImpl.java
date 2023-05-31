package com.itstudy.service.impl;

import com.itstudy.dao.BookDao;
import com.itstudy.dao.UserDao;
import com.itstudy.service.BookService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BookServiceImpl implements BookService {
    private UserDao userDao;
    private BookDao bookDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void save() {
        System.out.println("book service save...");
        bookDao.save();
        userDao.save();
    }
}
