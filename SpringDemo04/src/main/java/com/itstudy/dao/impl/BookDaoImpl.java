package com.itstudy.dao.impl;

import com.itstudy.dao.BookDao;

public class BookDaoImpl implements BookDao{
    private int connectionNum;
    private String databaseName;

    public void setConnectionNum(int connectionNum) {
        this.connectionNum = connectionNum;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public void save() {
        System.out.println("book dao save..." + databaseName + ", " + connectionNum);
    }
}
