package com.itstudy;

import com.itstudy.dao.UserDao;
import com.itstudy.factory.UseDaoFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppForInstanceUser {
    public static void main(String[] args) {
/*        //创建实例工厂对象
        UseDaoFactory useDaoFactory = new UseDaoFactory();
        //通过实例工厂创造对象
        UserDao userDao = useDaoFactory.getUserDao();
        userDao.save();*/

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserDao userDao1 = (UserDao) ctx.getBean("userDao");
        UserDao userDao2 = (UserDao) ctx.getBean("userDao");
        System.out.println(userDao1);
        System.out.println(userDao2);
        /*创造出的对象是单例, 在UserDaoFactoryBean里可以重写isSingleton方法来实现多例
        @399c4be1
        @291caca8*/
        //userDao.save();
    }
}
