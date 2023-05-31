package com.itstudy.dao.impl;

import com.itstudy.dao.BookDao;

public class BookDaoImpl implements BookDao{

    @Override
    public void save() {
        System.out.println("book dao save...");
    }
}
