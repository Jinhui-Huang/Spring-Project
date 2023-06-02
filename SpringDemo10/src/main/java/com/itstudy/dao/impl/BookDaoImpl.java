package com.itstudy.dao.impl;

import com.itstudy.dao.BookDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component("bookDao")
@Repository("bookDao")
@Scope()
public class BookDaoImpl implements BookDao{

    @Override
    public void save() {
        System.out.println("book dao save...");
    }

    @PostConstruct
    public void init() {
        System.out.println("book dao init...");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("book dao destroy...");
    }


}
