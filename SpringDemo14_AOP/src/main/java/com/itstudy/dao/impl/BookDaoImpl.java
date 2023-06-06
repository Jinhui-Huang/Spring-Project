package com.itstudy.dao.impl;

import com.itstudy.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao{

    @Override
    public void save() {
        System.out.println(System.currentTimeMillis());
        System.out.println("book dao save...");
    }

    @Override
    public void update() {
            System.out.println("book dao update...");
    }

    @Override
    public int select() {
        System.out.println("book dao select");
        int i = 1/0;//异常抛出测试
        return 100;
    }

    @Override
    public int findName(String name) {
        System.out.println("name: " + name);
        //int i = 1/0; //异常测试
        return 666;
    }

    @Override
    public boolean confirmPassword(String password) {
        return password.trim().equals(password);
    }}
