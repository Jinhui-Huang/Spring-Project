package com.itstudy.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.itstudy.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    @Value("com.mysql.jdbc.Driver")
    private String driver;

    @Value("jdbc:mysql://localhost:3306/spring_db")
    private String url;

    @Value("root")
    private String username;

    @Value("root")
    private String password;


    //1, 定义一个方法获得要管理的bean
    //2, 添加@Bean表示返回的是一个Bean
    @Bean
    public DataSource dataSource(BookDao bookDao) {
        System.out.println(bookDao);
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
