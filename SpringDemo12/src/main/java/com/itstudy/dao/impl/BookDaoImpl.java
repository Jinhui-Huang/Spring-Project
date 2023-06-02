package com.itstudy.dao.impl;

import com.itstudy.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("Book Dao save...");
    }
}
