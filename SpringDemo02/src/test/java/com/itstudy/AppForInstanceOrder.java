package com.itstudy;

import com.itstudy.dao.OrderDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForInstanceOrder {
    public static void main(String[] args) {
        //静态工厂创造对象
/*        OrderDao orderDao = OrderDaoFactory.getOrderDao();
        orderDao.save();*/

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        OrderDao orderDao = (OrderDao) ctx.getBean("orderDao");
        
        orderDao.save();

    }
}
