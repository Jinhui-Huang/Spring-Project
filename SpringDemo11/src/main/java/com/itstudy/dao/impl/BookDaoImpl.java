package com.itstudy.dao.impl;

import com.itstudy.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("bookDao")
public class BookDaoImpl implements BookDao{

    @Override
    public void save() {
        System.out.println("book dao save...");
    }
}
