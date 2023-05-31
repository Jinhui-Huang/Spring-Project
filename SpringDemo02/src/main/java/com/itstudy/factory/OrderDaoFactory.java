package com.itstudy.factory;


import com.itstudy.dao.OrderDao;
import com.itstudy.dao.impl.OrderDaoImpl;

public class OrderDaoFactory {
    public static OrderDao getOrderDao() {
        System.out.println("factory setup...");
        return new OrderDaoImpl();
    }
}
