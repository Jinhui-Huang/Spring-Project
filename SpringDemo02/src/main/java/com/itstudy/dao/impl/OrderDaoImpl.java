package com.itstudy.dao.impl;

import com.itstudy.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {
    public OrderDaoImpl() {
        System.out.println("OrderDaoImpl construct is using");
    }

    @Override
    public void save() {
        System.out.println("Order Dao save");
    }
}
