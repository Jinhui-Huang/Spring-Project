package com.itstudy.service.impl;

import com.itstudy.dao.BookDao;
import com.itstudy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    //5, 删除业务层中创建的对象
    @Autowired
    @Qualifier("bookDao2")
    private BookDao bookDao;

    @Value("${name}")
    private String name;
    @Override
    public void save() {
        System.out.println("book service save..." + name);
        bookDao.save();
    }

}
