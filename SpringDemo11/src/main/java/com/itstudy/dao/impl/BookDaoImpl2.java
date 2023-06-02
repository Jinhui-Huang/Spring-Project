package com.itstudy.dao.impl;

import com.itstudy.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository("bookDao2")
public class BookDaoImpl2 implements BookDao{

    @Override
    public void save() {
        System.out.println("book dao save...2");
    }
}
