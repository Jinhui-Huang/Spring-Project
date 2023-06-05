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
        for (int i = 0; i < 10000; i++) {
            System.out.println("book dao update...");
        }
    }

    @Override
    public int select() {
        System.out.println("book dao select");
        //int i = 1/0;//异常抛出测试
        return 100;
    }
}
