package com.itstudy.dao.impl;

import com.itstudy.dao.UserDao;

public class UserDaoImpl implements UserDao {
    public UserDaoImpl() {
        System.out.println("UserDaoImpl construct is running");
    }

    @Override
    public void save() {
        System.out.println("User Dao save...");
    }
}
