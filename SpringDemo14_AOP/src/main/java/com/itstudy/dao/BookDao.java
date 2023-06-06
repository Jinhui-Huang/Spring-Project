package com.itstudy.dao;

public interface BookDao {
    void save();
    void update();

    int select();

    int findName(String name);

    boolean confirmPassword(String password);
}
