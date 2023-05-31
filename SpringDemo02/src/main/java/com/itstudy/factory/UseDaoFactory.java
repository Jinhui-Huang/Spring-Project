package com.itstudy.factory;

import com.itstudy.dao.UserDao;
import com.itstudy.dao.impl.UserDaoImpl;

public class UseDaoFactory {
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }
}
