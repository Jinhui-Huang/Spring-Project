package com.itstudy.factory;

import com.itstudy.dao.UserDao;
import com.itstudy.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.FactoryBean;

public class UserDaoFactoryBean implements FactoryBean<UserDao> {
    //代替原始实例工厂中创建对象的方法
    @Override
    public UserDao getObject() throws Exception {
        return new UserDaoImpl();
    }
    //返回你要创造类的类型字节码class(一般接口的class)
    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        //true 单例
        //false 非单例
        return true;
    }
}
