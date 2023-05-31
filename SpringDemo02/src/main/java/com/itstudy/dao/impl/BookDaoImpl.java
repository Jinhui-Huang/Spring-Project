package com.itstudy.dao.impl;

import com.itstudy.dao.BookDao;

public class BookDaoImpl implements BookDao{

    private BookDaoImpl() {
        System.out.println("book dao construct is running");
    }

    @Override
    public void save() {
        System.out.println("book dao save...");
    }
}
