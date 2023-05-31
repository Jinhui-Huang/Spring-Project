package com.itstudy.dao.impl;

import com.itstudy.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("User Dao save...");
    }
}
