package com.itstudy.dao.impl;

import com.itstudy.dao.BookDao;

public class BookDaoImpl implements BookDao{
    @Override
    public void save() {
        System.out.println("book dao save...");
    }

    //bean创造前初始化对应的操作
    public void init() {
        System.out.println("init...");
    }

    //bean销毁前对应的操作
    public void destroy() {
        System.out.println("destroy...");
    }
}
